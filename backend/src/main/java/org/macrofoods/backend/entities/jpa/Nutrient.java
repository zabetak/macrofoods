package org.macrofoods.backend.entities.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Nutrient {
	@Id
	private int id;
	@Column(length = 7, nullable = false)
	private String units;
	@Column(length = 20, nullable = true)
	private String tagName;
	@Column(length = 60, nullable = false)
	private String nDesc;
	@Column(length = 30, nullable = true)
	private String sDesc;
	@Column(nullable = false)
	private short valueScale;
	@Column(precision = 6, scale = 0, nullable = false)
	private BigDecimal srOrder;

	public Nutrient() {
	}
}
