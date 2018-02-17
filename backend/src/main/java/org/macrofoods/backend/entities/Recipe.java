package org.macrofoods.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Recipe {
	@Id
	private Integer id;
	@Column(nullable = false, unique = true, length = 60)
	private String nid;
	@Column
	private Integer views;
	@Column
	private Integer likes;
	@Column
	private Short score;
	@Column
	private Short prepTime;
	@Column
	private Short cookTime;
	@Enumerated(EnumType.ORDINAL)
	private Difficulty difficulty;
	@Column
	private Short servings;
	@Column(nullable = false)
	private byte[] image;

	public Recipe() {
	}
}
