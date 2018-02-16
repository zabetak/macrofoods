package org.macrofoods.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class FoodGroup {

	@Id
	private int id;

	public FoodGroup() {
	}

	public FoodGroup(int aId) {
		this.id = aId;
	}
}
