package org.macrofoods.backend.dto;

import java.math.BigDecimal;

public final class FoodNMacrosDTO {
	private final int id;
	private final String description;
	private final String category;
	private final BigDecimal kcal;
	private final BigDecimal protein;
	private final BigDecimal carbohydrate;
	private final BigDecimal fat;

	public FoodNMacrosDTO(int id, String description, String category, BigDecimal kcal, BigDecimal protein,
			BigDecimal carb, BigDecimal fat) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.kcal = kcal;
		this.protein = protein;
		this.carbohydrate = carb;
		this.fat = fat;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public BigDecimal getKcal() {
		return kcal;
	}

	public BigDecimal getProtein() {
		return protein;
	}

	public BigDecimal getCarbohydrate() {
		return carbohydrate;
	}

	public BigDecimal getFat() {
		return fat;
	}

}
