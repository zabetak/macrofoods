package org.macrofoods.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Food")
public class Food {

	@Id
	private int id;
	@ManyToOne
	private FoodGroup fdGroup;
	private boolean isSurvey;
	private short refuse;
	private float nFactor;
	private float protFactor;
	private float fatFactor;
	private float choFactor;

	public Food() {
	}

	public Food(int id, FoodGroup fdGroup, boolean isSurvey, short refuse, float nFactor, float protFactor,
			float fatFactor, float choFactor) {
		super();
		this.id = id;
		this.fdGroup = fdGroup;
		this.isSurvey = isSurvey;
		this.refuse = refuse;
		this.nFactor = nFactor;
		this.protFactor = protFactor;
		this.fatFactor = fatFactor;
		this.choFactor = choFactor;
	}

}
