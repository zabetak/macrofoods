package org.macrofoods.backend.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Food {
	@Id
	private int id;
	@ManyToOne(optional = false)
	private FoodGroup fdGroup;
	@Column(nullable = true)
	private boolean isSurvey;
	@Column(nullable = true)
	private short refuse;
	@Column(precision = 4, scale = 2, nullable = true)
	private BigDecimal nFactor;
	@Column(precision = 4, scale = 2, nullable = true)
	private BigDecimal proFactor;
	@Column(precision = 4, scale = 2, nullable = true)
	private BigDecimal fatFactor;
	@Column(precision = 4, scale = 2, nullable = true)
	private BigDecimal choFactor;

	public Food() {
	}

}
