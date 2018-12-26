package org.macrofoods.backend.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.macrofoods.backend.dto.IngredientDTO;
import org.macrofoods.backend.dto.IngredientGroupDTO;
import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.dto.StepDTO;
import org.macrofoods.backend.dto.StepGroupDTO;
import org.macrofoods.backend.dto.TagDTO;
import org.macrofoods.backend.entities.jpa.Difficulty;
import org.macrofoods.backend.entities.jpa.Food;
import org.macrofoods.backend.entities.jpa.Ingredient;
import org.macrofoods.backend.entities.jpa.IngredientGroup;
import org.macrofoods.backend.entities.jpa.IngredientGroupDescription;
import org.macrofoods.backend.entities.jpa.LangCode;
import org.macrofoods.backend.entities.jpa.Recipe;
import org.macrofoods.backend.entities.jpa.RecipeDescription;
import org.macrofoods.backend.entities.jpa.Step;
import org.macrofoods.backend.entities.jpa.StepDescription;
import org.macrofoods.backend.entities.jpa.StepGroup;
import org.macrofoods.backend.entities.jpa.StepGroupDescription;
import org.macrofoods.backend.entities.jpa.Tag;
import org.macrofoods.backend.entities.jpa.TagDescription;

public final class RecipeService {

	private final EntityManager em;

	public RecipeService(EntityManager entityManager) {
		this.em = entityManager;
	}

	public int saveRecipe(RecipeDTO recipe) {
		LangCode lCode = LangCode.US;
		em.getTransaction().begin();
		Recipe eRecipe = new Recipe();
		eRecipe.setCookTime(recipe.getCookTime());
		eRecipe.setPrepTime(recipe.getPrepTime());
		eRecipe.setDifficulty(Difficulty.values()[recipe.getDifficulty()]);
		eRecipe.setServings(recipe.getServings());
		eRecipe.setImage(recipe.getImage().getBytes());
		em.persist(eRecipe);
		RecipeDescription eDescription = new RecipeDescription();
		eDescription.setRecipe(eRecipe);
		eDescription.setTitle(recipe.getTitle());
		eDescription.setSummary(recipe.getSummary());
		eDescription.setConclusion(recipe.getConclusion());
		eDescription.setLangCode(lCode);
		em.persist(eDescription);
		List<IngredientGroupDTO> ingGroups = recipe.getIngGroups();
		for (short i = 0; i < ingGroups.size(); i++) {
			String groupName = ingGroups.get(i).getName();
			IngredientGroup eiGroup = new IngredientGroup();
			eiGroup.setRecipe(eRecipe);
			eiGroup.setSeq(i);
			em.persist(eiGroup);
			IngredientGroupDescription eigDescription = new IngredientGroupDescription();
			eigDescription.setGroup(eiGroup);
			eigDescription.setLangCode(lCode);
			eigDescription.setTitle(groupName);
			em.persist(eigDescription);
			List<IngredientDTO> ingredients = ingGroups.get(i).getIngredients();
			for (short j = 0; j < ingredients.size(); j++) {
				Ingredient eIngredient = new Ingredient();
				IngredientDTO dtoIngredient = ingredients.get(j);
				eIngredient.setAmount(dtoIngredient.getAmount());
				Food eFood = em.find(Food.class, dtoIngredient.getFood().getId());
				eIngredient.setFood(eFood);
				eIngredient.setGroup(eiGroup);
				eIngredient.setSeq(j);
				em.persist(eIngredient);
			}
		}

		List<StepGroupDTO> stepGroupsDTO = recipe.getStepGroups();
		for (short i = 0; i < stepGroupsDTO.size(); i++) {
			String groupName = stepGroupsDTO.get(i).getName();
			StepGroup eGroup = new StepGroup();
			eGroup.setRecipe(eRecipe);
			eGroup.setSeq(i);
			em.persist(eGroup);
			StepGroupDescription eigDescription = new StepGroupDescription();
			eigDescription.setGroup(eGroup);
			eigDescription.setLangCode(lCode);
			eigDescription.setTitle(groupName);
			em.persist(eigDescription);
			List<StepDTO> stepsDTO = stepGroupsDTO.get(i).getSteps();
			for (short j = 0; j < stepsDTO.size(); j++) {
				String stepName = stepsDTO.get(j).getDescription();
				Step eStep = new Step();
				eStep.setGroup(eGroup);
				eStep.setSeq(j);
				em.persist(eStep);
				StepDescription esDescription = new StepDescription();
				esDescription.setStep(eStep);
				esDescription.setLangCode(lCode);
				esDescription.setDescription(stepName);
				em.persist(esDescription);
			}
		}

		for (TagDTO tagDTO : recipe.getTags()) {
			Tag tag;
			if (tagDTO.getId() == -1) {
				tag = new Tag();
				em.persist(tag);
			} else
				tag = em.find(Tag.class, tagDTO.getId());
			boolean hasDesc = false;
			for (TagDescription tg : tag.getDescriptions()) {
				if (tg.getName().equals(tagDTO.getDescription())) {
					hasDesc = true;
					break;
				}
			}
			if (!hasDesc) {
				TagDescription tagDesc = new TagDescription();
				tagDesc.setLangCode(lCode);
				tagDesc.setName(tagDTO.getDescription());
				tagDesc.setTag(tag);
				em.persist(tagDesc);
			}
			eRecipe.getTags().add(tag);
		}
		em.getTransaction().commit();
		return eRecipe.getId();
	}

	public List<RecipeDTO> topRecipes() {
		LangCode lCode = LangCode.US;
		// TODO Unify langcodes US/EN
		FoodsService fs = new FoodsService(em, LangCode.EN);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> q = builder.createQuery(Recipe.class);
		q.from(Recipe.class);
		TypedQuery<Recipe> query = em.createQuery(q);
		List<RecipeDTO> recipes = new ArrayList<RecipeDTO>();
		for (Recipe eRecipe : query.getResultList()) {
			RecipeDTO sample = new RecipeDTO();
			Short cookTime = eRecipe.getCookTime();
			if (cookTime != null)
				sample.setCookTime(cookTime);
			Short prepTime = eRecipe.getPrepTime();
			if (prepTime != null)
				sample.setPrepTime(prepTime);
			byte[] img = eRecipe.getImage();
			if (img != null)
				sample.setImage(new String(img));
			Short servings = eRecipe.getServings();
			if (servings != null)
				sample.setServings(servings);
			for (RecipeDescription rDescription : eRecipe.getDescriptions())
				if (rDescription.getLangCode().equals(lCode)) {
					sample.setConclusion(rDescription.getConclusion());
					sample.setTitle(rDescription.getTitle());
					sample.setSummary(rDescription.getSummary());
				}
			List<IngredientGroupDTO> ingGroups = new ArrayList<IngredientGroupDTO>();
			for (IngredientGroup rGroup : eRecipe.getIngGroups()) {
				IngredientGroupDTO iGroupDTO = new IngredientGroupDTO();
				for (IngredientGroupDescription rGroupDescription : rGroup.getDescriptions())
					if (rGroupDescription.getLangCode().equals(lCode))
						iGroupDTO.setName(rGroupDescription.getTitle());
				List<IngredientDTO> ingredientDTOs = new ArrayList<IngredientDTO>();
				for (Ingredient ing : rGroup.getIngredients()) {
					IngredientDTO ingDTO = new IngredientDTO();
					ingDTO.setAmount(ing.getAmount());
					Food food = ing.getFood();
					ingDTO.setFood(fs.findFood(food.getId()));
					ingredientDTOs.add(ingDTO);
				}
				iGroupDTO.setIngredients(ingredientDTOs);
				ingGroups.add(iGroupDTO);
			}
			sample.setIngGroups(ingGroups);
			List<TagDTO> tagDTOs = new ArrayList<TagDTO>();
			for (Tag tag : eRecipe.getTags()) {
				TagDTO tg = new TagDTO();
				for (TagDescription tgDesc : tag.getDescriptions()) {
					if (tgDesc.getLangCode().equals(lCode)) {
						tg.setDescription(tgDesc.getName());
						tagDTOs.add(tg);
					}
				}
			}
			sample.setTags(tagDTOs);
			recipes.add(sample);
		}
		return recipes;
	}
}
