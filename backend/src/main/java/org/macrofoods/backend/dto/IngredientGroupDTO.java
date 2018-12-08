package org.macrofoods.backend.dto;

import java.util.List;

public class IngredientGroupDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	private List<IngredientDTO> ingredients;
}
