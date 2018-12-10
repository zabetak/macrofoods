package org.macrofoods.backend.entities.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
	@SequenceGenerator(name = "ingredient_generator", sequenceName = "ingredient_seq", allocationSize = 10)
	private Integer id;
	@Column(nullable = false)
	private Short seq;
	@ManyToOne(optional = false)
	private IngredientGroup group;
	@Column(nullable = false, precision = 7, scale = 3)
	private BigDecimal amount;
	@ManyToOne(optional = false)
	private Food food;

	public Ingredient() {
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public IngredientGroup getGroup() {
		return group;
	}

	public void setGroup(IngredientGroup group) {
		this.group = group;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

}
