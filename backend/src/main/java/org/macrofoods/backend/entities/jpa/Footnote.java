package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Footnote {

	public enum Type {
		/**
		 * Footnote adding information to the food description
		 */
		DESCRIPTION,
		/**
		 * Footnote adding information to measure description
		 */
		MEASURE,
		/**
		 * Footnote providing additional information on a nutrient value.
		 */
		NUTRIENT
	};

	@Id
	private int id;
	@ManyToOne(optional = false)
	private Food food;
	@ManyToOne(optional = true)
	private Nutrient nutrient;
	@Column(nullable = false)
	private short fnumber;
	@Enumerated(EnumType.ORDINAL)
	/**
	 * If the type is N, the nutrient will also be filled in.
	 */
	private Type ftype;
	@Column(length = 200, nullable = false)
	private String text;

	public Footnote() {
	}

}
