package org.macrofoods.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FoodGroup")
public class FoodGroup {

	@Id
	private int id;

	public FoodGroup() {
	}

	public FoodGroup(int aId) {
		this.id = aId;
	}

	@Override
	public String toString() {
		return "FG" + Integer.toString(id);
	}
}
