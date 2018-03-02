package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StepDescription {
	@Id
	private Integer id;
	@ManyToOne(optional = false)
	private Step step;
	@Column(nullable = false, length = 1000)
	private String description;
	@Enumerated(EnumType.STRING)
	private LangCode langCode;

	public StepDescription() {
	}
}
