package org.macrofoods.backend.dto;

import java.util.List;

public final class RecipeDTO {
	private Integer id;
	private String title;
	private String summary;
	private String conclusion;
	private Short prepTime;
	private Short cookTime;
	private Short servings;
	private ImageDTO image;
	private Short difficulty;
	private List<IngredientGroupDTO> ingGroups;
	private List<StepGroupDTO> stepGroups;
	private List<TagDTO> tags;

	public void setId(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

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

	public Short getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(short prepTime) {
		this.prepTime = prepTime;
	}

	public Short getCookTime() {
		return cookTime;
	}

	public void setCookTime(short cookTime) {
		this.cookTime = cookTime;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public Short getDifficulty() {
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

	public Short getServings() {
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
