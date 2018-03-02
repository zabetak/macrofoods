package org.macrofoods.backend.entities.jpa;

import java.io.Serializable;

/**
 */
public class WeightId implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer food;

	private Integer seq;

	public WeightId() {
	}

	public Integer getFood() {
		return this.food;
	}

	public void setFood(Integer food) {
		this.food = food;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WeightId)) {
			return false;
		}
		WeightId castOther = (WeightId) other;
		return this.food.equals(castOther.food) && this.seq.equals(castOther.seq);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.food.hashCode();
		hash = hash * prime + this.seq.hashCode();

		return hash;
	}
}