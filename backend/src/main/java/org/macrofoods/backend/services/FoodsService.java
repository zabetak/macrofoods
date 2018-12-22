package org.macrofoods.backend.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.macrofoods.backend.dto.FoodDTO;
import org.macrofoods.backend.dto.MacrosDTO;
import org.macrofoods.backend.entities.jpa.Food;
import org.macrofoods.backend.entities.jpa.FoodDescription;
import org.macrofoods.backend.entities.jpa.FoodDescription_;
import org.macrofoods.backend.entities.jpa.FoodGroup;
import org.macrofoods.backend.entities.jpa.FoodGroupDescription;
import org.macrofoods.backend.entities.jpa.FoodGroupDescription_;
import org.macrofoods.backend.entities.jpa.FoodGroup_;
import org.macrofoods.backend.entities.jpa.Food_;
import org.macrofoods.backend.entities.jpa.Nutrient;
import org.macrofoods.backend.entities.jpa.NutrientData;
import org.macrofoods.backend.entities.jpa.NutrientData_;
import org.macrofoods.backend.entities.jpa.Nutrient_;

public final class FoodsService {

	private final EntityManager em;

	public FoodsService(EntityManager entityManager) {
		this.em = entityManager;
	}

	public List<FoodDTO> findFoods(String descriptionPattern) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = builder.createTupleQuery();
		Root<Food> food = q.from(Food.class);
		Join<Food, FoodDescription> fDescription = food.join(Food_.descriptions);
		Join<Food, FoodGroup> fGroup = food.join(Food_.fdGroup);
		Join<FoodGroup, FoodGroupDescription> fgDescription = fGroup.join(FoodGroup_.descriptions);
		Join<Food, NutrientData> fData = food.join(Food_.data, JoinType.LEFT);
		Join<NutrientData, Nutrient> nutrient = fData.join(NutrientData_.nutrient);
		q.where(builder.and(
				builder.like(builder.upper(fDescription.get(FoodDescription_.longDesc).as(String.class)),
						descriptionPattern),
				builder.or(builder.equal(nutrient.get(Nutrient_.tagName), "PROCNT"),
						builder.equal(nutrient.get(Nutrient_.tagName), "CHOCDF"),
						builder.equal(nutrient.get("tagName"), "FAT"),
						builder.equal(nutrient.get(Nutrient_.tagName), "ENERC_KCAL"))));
		q.groupBy(food.get("id"), fDescription.get(FoodDescription_.longDesc),
				fgDescription.get(FoodGroupDescription_.shortDesc));
		q.multiselect(food.get(Food_.id), fDescription.get(FoodDescription_.longDesc),
				fgDescription.get(FoodGroupDescription_.shortDesc),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "ENERC_KCAL"),
								fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "PROCNT"), fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "CHOCDF"), fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "FAT"), fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)));

		TypedQuery<Tuple> query = em.createQuery(q);
		List<FoodDTO> results = new ArrayList<FoodDTO>();
		for (Tuple t : query.getResultList()) {
			Integer id = t.get(0, Integer.class);
			String desc = t.get(1, String.class);
			String category = t.get(2, String.class);
			MacrosDTO macros = new MacrosDTO();
			macros.setCalories(t.get(3, BigDecimal.class));
			macros.setProtein(t.get(4, BigDecimal.class));
			macros.setCarbs(t.get(5, BigDecimal.class));
			macros.setFat(t.get(6, BigDecimal.class));
			results.add(new FoodDTO(id, desc, category, macros));
		}
		return results;
	}
}
