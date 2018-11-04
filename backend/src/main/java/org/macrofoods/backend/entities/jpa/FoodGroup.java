package org.macrofoods.backend.entities.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class FoodGroup {

	@Id
	private int id;
	@OneToMany(mappedBy = "fdGroup")
	private List<Food> foods;
	@OneToMany(mappedBy = "group")
	private List<FoodGroupDescription> descriptions;

	public FoodGroup() {
	}

	public FoodGroup(int aId) {
		this.id = aId;
	}
}
