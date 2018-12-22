package org.macrofoods.backend.dto;

import java.math.BigDecimal;

public final class IngredientDTO {
	private BigDecimal amount;
	private FoodDTO food;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public FoodDTO getFood() {
		return food;
	}

	public void setFood(FoodDTO food) {
		this.food = food;
	}
}
