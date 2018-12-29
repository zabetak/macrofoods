package org.macrofoods.backend.dto;

import java.util.List;

public class NutritionalFactsDTO {
	private List<NutrientDTO> nutrients;

	public List<NutrientDTO> getNutrients() {
		return nutrients;
	}

	public void setNutrients(List<NutrientDTO> nutrients) {
		this.nutrients = nutrients;
	}

}
