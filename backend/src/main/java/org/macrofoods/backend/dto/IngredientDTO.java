package org.macrofoods.backend.dto;

import java.math.BigDecimal;

public final class IngredientDTO {
	private BigDecimal amount;
	private FoodNMacrosDTO food;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public FoodNMacrosDTO getFood() {
		return food;
	}

	public void setFood(FoodNMacrosDTO food) {
		this.food = food;
	}
}
