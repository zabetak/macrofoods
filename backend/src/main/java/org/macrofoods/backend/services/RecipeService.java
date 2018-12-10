package org.macrofoods.backend.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.macrofoods.backend.dto.IngredientDTO;
import org.macrofoods.backend.dto.IngredientGroupDTO;
import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.dto.StepDTO;
import org.macrofoods.backend.dto.StepGroupDTO;
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

public final class RecipeService {

	private final EntityManager em;

	public RecipeService(EntityManager entityManager) {
		this.em = entityManager;
	}

	public int saveRecipe(RecipeDTO recipe) {
		em.getTransaction().begin();
		Recipe eRecipe = new Recipe();
		eRecipe.setCookTime(recipe.getCookTime());
		eRecipe.setPrepTime(recipe.getPrepTime());
		eRecipe.setDifficulty(Difficulty.values()[recipe.getDifficulty()]);
		eRecipe.setImage(recipe.getImage().getBytes());
		em.persist(eRecipe);
		RecipeDescription eDescription = new RecipeDescription();
		eDescription.setRecipe(eRecipe);
		eDescription.setTitle(recipe.getTitle());
		eDescription.setSummary(recipe.getSummary());
		eDescription.setConclusion(recipe.getConclusion());
		eDescription.setLangCode(LangCode.US);
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
			eigDescription.setLangCode(LangCode.US);
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
			eigDescription.setLangCode(LangCode.US);
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
				esDescription.setLangCode(LangCode.US);
				esDescription.setDescription(stepName);
				em.persist(esDescription);
			}
		}

		em.getTransaction().commit();
		return eRecipe.getId();
	}
}
