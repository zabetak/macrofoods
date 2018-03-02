package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class FoodGroupDescription {

	@Id
	private int id;
	@ManyToOne(optional = false)
	private FoodGroup group;
	@Column(length = 60, nullable = false)
	private String shortDesc;
	@Column(length = 2, nullable = false)
	private String langCode;

	public FoodGroupDescription() {
	}
}
