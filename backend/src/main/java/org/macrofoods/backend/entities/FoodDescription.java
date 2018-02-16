package org.macrofoods.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class FoodDescription {

	@Id
	private int id;
	@ManyToOne(optional = false)
	private Food food;
	@Column(length = 200, nullable = false)
	private String longDesc;
	@Column(length = 60, nullable = false)
	private String shortDesc;
	@Column(length = 100)
	private String commonName;
	@Column(length = 65)
	private String manuName;
	@Column(length = 135)
	private String refDesc;
	@Column(length = 65)
	private String sciName;
	@Column(length = 2, nullable = false)
	private String langCode;

	public FoodDescription() {
	}
}
