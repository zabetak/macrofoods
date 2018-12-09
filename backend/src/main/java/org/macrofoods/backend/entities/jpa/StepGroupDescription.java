package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StepGroupDescription {
	@Id
	private Integer id;
	@ManyToOne(optional = false)
	private StepGroup group;
	@Column(length = 60)
	private String title;
	@Enumerated(EnumType.STRING)
	private LangCode langCode;

	public StepGroupDescription() {
	}

}
