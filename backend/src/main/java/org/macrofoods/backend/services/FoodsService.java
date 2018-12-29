package org.macrofoods.backend.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.macrofoods.backend.dto.FoodDTO;
import org.macrofoods.backend.dto.NutrientDTO;
import org.macrofoods.backend.dto.NutritionalFactsDTO;
import org.macrofoods.backend.entities.jpa.Food;
import org.macrofoods.backend.entities.jpa.FoodDescription;
import org.macrofoods.backend.entities.jpa.FoodDescription_;
import org.macrofoods.backend.entities.jpa.FoodGroup;
import org.macrofoods.backend.entities.jpa.FoodGroupDescription;
import org.macrofoods.backend.entities.jpa.FoodGroupDescription_;
import org.macrofoods.backend.entities.jpa.FoodGroup_;
import org.macrofoods.backend.entities.jpa.Food_;
import org.macrofoods.backend.entities.jpa.LangCode;
import org.macrofoods.backend.entities.jpa.Nutrient;
import org.macrofoods.backend.entities.jpa.NutrientData;
import org.macrofoods.backend.entities.jpa.NutrientData_;
import org.macrofoods.backend.entities.jpa.Nutrient_;

public final class FoodsService {

	private final EntityManager em;
	private final TypedQuery<Tuple> foodById;
	private final TypedQuery<Tuple> foodByDescription;
	private static final String FOOD_ID_PARAM = "fid";
	private static final String FOOD_DESC_PARAM = "fdesc";

	public FoodsService(EntityManager entityManager, LangCode langCode) {
		String lCode = langCode.toString();
		this.em = entityManager;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = builder.createTupleQuery();
		Root<Food> food = q.from(Food.class);
		Join<Food, FoodDescription> fDescription = food.join(Food_.descriptions);
		Join<Food, FoodGroup> fGroup = food.join(Food_.fdGroup);
		Join<FoodGroup, FoodGroupDescription> fgDescription = fGroup.join(FoodGroup_.descriptions);
		Join<Food, NutrientData> fData = food.join(Food_.data, JoinType.LEFT);
		Join<NutrientData, Nutrient> nutrient = fData.join(NutrientData_.nutrient);

		Predicate nutrientPred = builder.conjunction();
		// List<String> nutrientTags = Arrays.asList("PROCNT", "CHOCDF", "FAT",
		// "ENERC_KCAL", "STARCH", "SUGAR", "FIBTG");
		List<String> nutrientTags = Collections.emptyList();
		for (String ntag : nutrientTags)
			nutrientPred = builder.or(nutrientPred, builder.equal(nutrient.get(Nutrient_.tagName), ntag));
		q.where(builder.and(builder.equal(food.get(Food_.id), builder.parameter(Integer.class, FOOD_ID_PARAM)),
				builder.equal(builder.upper(fDescription.get(FoodDescription_.langCode)), lCode), nutrientPred));
		q.orderBy(builder.asc(food.get(Food_.id)));
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(food.get(Food_.id));
		selections.add(fDescription.get(FoodDescription_.longDesc));
		selections.add(fgDescription.get(FoodGroupDescription_.shortDesc));
		selections.add(nutrient.get(Nutrient_.tagName));
		selections.add(nutrient.get(Nutrient_.nDesc));
		selections.add(fData.get(NutrientData_.amount));
		selections.add(nutrient.get(Nutrient_.units));

		q.multiselect(selections);

		foodById = em.createQuery(q);
		q.where(builder.and(
				builder.like(builder.upper(fDescription.get(FoodDescription_.longDesc).as(String.class)),
						builder.parameter(String.class, FOOD_DESC_PARAM)),
				builder.equal(builder.upper(fDescription.get(FoodDescription_.langCode)), lCode), nutrientPred));
		foodByDescription = em.createQuery(q);
	}

	public FoodDTO findFood(int id) {

		foodById.setParameter(FOOD_ID_PARAM, id);
		return buildFoodDTO(foodById).get(0);
	}

	public List<FoodDTO> findFoods(String descriptionPattern) {
		foodByDescription.setParameter(FOOD_DESC_PARAM, descriptionPattern);
		return buildFoodDTO(foodByDescription);
	}

	private List<FoodDTO> buildFoodDTO(TypedQuery<Tuple> query) {
		Map<Integer, List<Tuple>> nutrientsByFood = new HashMap<Integer, List<Tuple>>();
		for (Tuple t : query.getResultList()) {
			Integer fid = t.get(0, Integer.class);
			List<Tuple> tuples = nutrientsByFood.get(fid);
			if (tuples == null)
				tuples = new ArrayList<Tuple>();
			tuples.add(t);
			nutrientsByFood.put(fid, tuples);
		}

		List<FoodDTO> foods = new ArrayList<FoodDTO>();
		for (List<Tuple> nutrientsTuples : nutrientsByFood.values()) {
			List<NutrientDTO> nutrients = new ArrayList<NutrientDTO>();
			FoodDTO fd = new FoodDTO();
			for (Tuple t : nutrientsTuples) {
				if (nutrients.isEmpty()) {
					fd.setId(t.get(0, Integer.class));
					fd.setDescription(t.get(1, String.class));
					fd.setCategory(t.get(2, String.class));
				}
				String tag = t.get(3, String.class);
				String ndesc = t.get(4, String.class);
				BigDecimal amount = t.get(5, BigDecimal.class);
				String units = t.get(6, String.class);
				NutrientDTO nutrient = new NutrientDTO();
				nutrient.setTag(tag);
				nutrient.setName(ndesc);
				nutrient.setValue(amount);
				nutrient.setUnits(units);
				nutrients.add(nutrient);
			}
			if (!nutrients.isEmpty()) {
				NutritionalFactsDTO facts = new NutritionalFactsDTO();
				facts.setNutrients(nutrients);
				fd.setFacts(facts);
				foods.add(fd);
			}
		}
		return foods;
	}
}
