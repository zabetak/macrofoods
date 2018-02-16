package org.macrofoods.backend.entities;

import java.io.Serializable;

public class NutrientDataId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6802504558155311397L;
	private int food;
	private int nutrient;

	public NutrientDataId() {
	}

	public NutrientDataId(int food, int nutrient) {
		this.food = food;
		this.nutrient = nutrient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + food;
		result = prime * result + nutrient;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NutrientDataId other = (NutrientDataId) obj;
		if (food != other.food)
			return false;
		if (nutrient != other.nutrient)
			return false;
		return true;
	}

}
