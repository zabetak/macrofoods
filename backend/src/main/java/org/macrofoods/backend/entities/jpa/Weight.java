package org.macrofoods.backend.entities.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the "WEIGHT" database table.
 * 
 */
@Entity
@IdClass(WeightId.class)
public class Weight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Food food;
	@Id
	private Integer seq;

	@Column(nullable = false, precision = 7, scale = 3)
	private BigDecimal amount;

	@Column(nullable = false, precision = 7, scale = 1)
	private BigDecimal gm_wgt;

	@Column(nullable = false, length = 84)
	private String description;

	@Column
	private Integer dataPts;

	@Column(precision = 7, scale = 3)
	private BigDecimal std_Dev;

	public Weight() {
	}

}