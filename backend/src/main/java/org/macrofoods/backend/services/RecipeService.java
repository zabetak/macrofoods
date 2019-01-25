package org.macrofoods.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.macrofoods.backend.dto.ImageDTO;
import org.macrofoods.backend.dto.IngredientDTO;
import org.macrofoods.backend.dto.IngredientGroupDTO;
import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.dto.StepDTO;
import org.macrofoods.backend.dto.StepGroupDTO;
import org.macrofoods.backend.dto.TagDTO;
import org.macrofoods.backend.entities.jpa.Difficulty;
import org.macrofoods.backend.entities.jpa.Food;
import org.macrofoods.backend.entities.jpa.Image;
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
	private final FoodsService fs;
	private final LangCode lCode = LangCode.US;

	public RecipeService(EntityManager entityManager) {
		this.em = entityManager;
		// TODO Unify langcodes US/EN
		this.fs = new FoodsService(em, LangCode.EN);
	}

	public int saveRecipe(RecipeDTO dRecipe) {
		em.getTransaction().begin();
		Recipe eRecipe = new Recipe();
		updateEntityFromDTO(eRecipe, dRecipe);
		em.getTransaction().commit();
		return eRecipe.getId();
	}

	public int updateRecipe(RecipeDTO dRecipe) {
		em.getTransaction().begin();
		Recipe eRecipe = em.find(Recipe.class, dRecipe.getId());
		for (RecipeDescription eDescription : eRecipe.getDescriptions())
			em.remove(eDescription);
		for (IngredientGroup iGroup : eRecipe.getIngGroups())
			em.remove(iGroup);
		for (StepGroup sGroup : eRecipe.getStpGroups())
			em.remove(sGroup);
		eRecipe.getTags().clear();

		updateEntityFromDTO(eRecipe, dRecipe);
		em.getTransaction().commit();
		return eRecipe.getId();
	}

	public RecipeDTO findRecipe(int id) {
		Recipe eRecipe = em.find(Recipe.class, id);
		return eRecipe == null ? null : toDTO(eRecipe);
	}

	public List<RecipeDTO> topRecipes() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> q = builder.createQuery(Recipe.class);
		q.from(Recipe.class);
		TypedQuery<Recipe> query = em.createQuery(q);
		List<RecipeDTO> recipes = new ArrayList<RecipeDTO>();
		for (Recipe eRecipe : query.getResultList())
			recipes.add(toDTO(eRecipe));
		return recipes;
	}

	private void updateEntityFromDTO(Recipe eRecipe, RecipeDTO dRecipe) {
		eRecipe.setCookTime(dRecipe.getCookTime());
		eRecipe.setPrepTime(dRecipe.getPrepTime());
		eRecipe.setDifficulty(Difficulty.values()[dRecipe.getDifficulty()]);
		eRecipe.setServings(dRecipe.getServings());
		Image img = new Image();
		if (eRecipe.getImage() != null)
			img = eRecipe.getImage();
		img.setData(dRecipe.getImage().getData().getBytes());
		em.persist(img);
		eRecipe.setImage(img);
		em.persist(eRecipe);

		RecipeDescription eDescription = new RecipeDescription();
		eDescription.setRecipe(eRecipe);
		eDescription.setTitle(dRecipe.getTitle());
		eDescription.setSummary(dRecipe.getSummary());
		eDescription.setConclusion(dRecipe.getConclusion());
		eDescription.setLangCode(lCode);
		em.persist(eDescription);

		List<IngredientGroupDTO> ingGroups = dRecipe.getIngGroups();
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

		List<StepGroupDTO> stepGroupsDTO = dRecipe.getStepGroups();
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
		// TODO Check and fix the code below
		for (TagDTO tagDTO : dRecipe.getTags()) {
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
	}

	private RecipeDTO toDTO(Recipe eRecipe) {
		Objects.requireNonNull(eRecipe);
		RecipeDTO sample = new RecipeDTO();
		sample.setId(eRecipe.getId());
		Short cookTime = eRecipe.getCookTime();
		if (cookTime != null)
			sample.setCookTime(cookTime);
		Short prepTime = eRecipe.getPrepTime();
		if (prepTime != null)
			sample.setPrepTime(prepTime);
		Image img = eRecipe.getImage();
		if (img != null) {
			ImageDTO iDTO = new ImageDTO();
			iDTO.setId(img.getId());
			sample.setImage(iDTO);
		}
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

		List<StepGroupDTO> stpGroups = new ArrayList<StepGroupDTO>();
		for (StepGroup sGroup : eRecipe.getStpGroups()) {
			StepGroupDTO sGroupDTO = new StepGroupDTO();
			for (StepGroupDescription sGroupDescription : sGroup.getDescriptions())
				if (sGroupDescription.getLangCode().equals(lCode))
					sGroupDTO.setName(sGroupDescription.getTitle());
			List<StepDTO> stepDTOs = new ArrayList<StepDTO>();
			for (Step step : sGroup.getSteps()) {
				StepDTO stpDTO = new StepDTO();
				for (StepDescription stepDesc : step.getDescriptions())
					if (stepDesc.getLangCode().equals(lCode))
						stpDTO.setDescription(stepDesc.getDescription());
				stepDTOs.add(stpDTO);
			}
			sGroupDTO.setSteps(stepDTOs);
			stpGroups.add(sGroupDTO);
		}
		sample.setStepGroups(stpGroups);
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
		return sample;
	}
}
