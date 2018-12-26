package org.macrofoods.backend.dto;

import java.util.List;

public final class RecipeDTO {
	private String title;
	private String summary;
	private String conclusion;
	private short prepTime;
	private short cookTime;
	private short servings;
	private String image;
	private short difficulty;
	private List<IngredientGroupDTO> ingGroups;
	private List<StepGroupDTO> stepGroups;
	private List<TagDTO> tags;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public short getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(short prepTime) {
		this.prepTime = prepTime;
	}

	public short getCookTime() {
		return cookTime;
	}

	public void setCookTime(short cookTime) {
		this.cookTime = cookTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public short getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(short difficulty) {
		this.difficulty = difficulty;
	}

	public List<IngredientGroupDTO> getIngGroups() {
		return ingGroups;
	}

	public void setIngGroups(List<IngredientGroupDTO> ingGroup) {
		this.ingGroups = ingGroup;
	}

	public List<StepGroupDTO> getStepGroups() {
		return stepGroups;
	}

	public void setStepGroups(List<StepGroupDTO> stepGroup) {
		this.stepGroups = stepGroup;
	}

	public short getServings() {
		return servings;
	}

	public void setServings(short servings) {
		this.servings = servings;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

}
