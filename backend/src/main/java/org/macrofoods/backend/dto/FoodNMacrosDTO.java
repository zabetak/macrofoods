package org.macrofoods.backend.dto;

import java.math.BigDecimal;

public final class FoodNMacrosDTO {
	private int id;
	private String description;
	private String category;
	private BigDecimal kcal;
	private BigDecimal protein;
	private BigDecimal carbohydrate;
	private BigDecimal fat;

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setKcal(BigDecimal kcal) {
		this.kcal = kcal;
	}

	public void setProtein(BigDecimal protein) {
		this.protein = protein;
	}

	public void setCarbohydrate(BigDecimal carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat;
	}

	public FoodNMacrosDTO() {

	}

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
