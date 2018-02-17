package org.macrofoods.backend.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient {
	@Id
	private Integer id;
	@Column(nullable = false)
	private Short seq;
	@ManyToOne(optional = false)
	private IngredientGroup group;
	@Column(nullable = false, precision = 7, scale = 3)
	private BigDecimal amount;
	@ManyToOne(optional = false)
	private Weight weight;

	public Ingredient() {
	}
}
