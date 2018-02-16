package org.macrofoods.backend.entities;

import java.io.Serializable;

/**
 * 
 */
public class DatasrclnID implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer food;
	private Integer nutrient;
	private String datasrc;

	public DatasrclnID() {
	}

	public Integer getNDB_No() {
		return this.food;
	}

	public void setNDB_No(Integer NDB_No) {
		this.food = NDB_No;
	}

	public Integer getNutr_No() {
		return this.nutrient;
	}

	public void setNutr_No(Integer nutr_No) {
		this.nutrient = nutr_No;
	}

	public String getDataSrc_ID() {
		return this.datasrc;
	}

	public void setDataSrc_ID(String dataSrc_ID) {
		this.datasrc = dataSrc_ID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatasrclnID)) {
			return false;
		}
		DatasrclnID castOther = (DatasrclnID) other;
		return this.food.equals(castOther.food) && this.nutrient.equals(castOther.nutrient)
				&& this.datasrc.equals(castOther.datasrc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.food.hashCode();
		hash = hash * prime + this.nutrient.hashCode();
		hash = hash * prime + this.datasrc.hashCode();

		return hash;
	}
}