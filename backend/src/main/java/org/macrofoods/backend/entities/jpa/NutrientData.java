package org.macrofoods.backend.entities.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(NutrientDataId.class)
public class NutrientData {

	@Id
	@ManyToOne
	private Food food;
	@Id
	@ManyToOne
	private Nutrient nutrient;

	/**
	 * Amount in 100 grams
	 */
	@Column(precision = 10, scale = 3, nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private short dataPoints;
	@Column(precision = 8, scale = 3, nullable = true)
	private BigDecimal stdError;
	/*
	 * Takes the following values:8, 9, 13, 5, 1, 7, 4, 6, 11, 12 TODO: It would be
	 * better as an enumerated type if we find the meaning
	 */
	@Column(nullable = false)
	private short srcCode;
	/*
	 * TODO: 56 values in total, enumeration?
	 */
	@Column(length = 4, nullable = true)
	private String derivCode;
	@ManyToOne(optional = true)
	private Food missingValueReference;
	@Column(nullable = true)
	private Boolean isEnriched;
	@Column(nullable = true)
	private short numOfStudies;
	@Column(precision = 10, scale = 3, nullable = true)
	private BigDecimal minVal;
	@Column(precision = 10, scale = 3, nullable = true)
	private BigDecimal maxVal;
	@Column(nullable = true)
	private short freedomDegree;
	@Column(precision = 10, scale = 3, nullable = true)
	private BigDecimal lowerErrorBound;
	@Column(precision = 10, scale = 3, nullable = true)
	private BigDecimal upperErrorBound;
	@Column(length = 10, nullable = true)
	private String statsComments;
	// TODO: Consider transforming string to date
	@Column(length = 10, nullable = true)
	private String modificationDate;
	@Enumerated(EnumType.ORDINAL)
	private ConfidenceCode cc;

	public NutrientData() {
	}
}
