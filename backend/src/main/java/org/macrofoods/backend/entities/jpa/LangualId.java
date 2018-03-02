package org.macrofoods.backend.entities.jpa;

import java.io.Serializable;

/**
 * 
 */
public class LangualId implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer food;

	private String langdesc;

	public LangualId() {
	}

	public Integer getFood() {
		return this.food;
	}

	public void setFood(Integer food) {
		this.food = food;
	}

	public String getFactor_Code() {
		return this.langdesc;
	}

	public void setFactor_Code(String factor_Code) {
		this.langdesc = factor_Code;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LangualId)) {
			return false;
		}
		LangualId castOther = (LangualId) other;
		return this.food.equals(castOther.food) && this.langdesc.equals(castOther.langdesc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.food.hashCode();
		hash = hash * prime + this.langdesc.hashCode();

		return hash;
	}
}