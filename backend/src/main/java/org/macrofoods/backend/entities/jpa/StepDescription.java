package org.macrofoods.backend.entities.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class StepDescription {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sdescription_generator")
	@SequenceGenerator(name = "sdescription_generator", sequenceName = "step_description_seq", allocationSize = 10)
	private Integer id;
	@ManyToOne(optional = false, cascade = CascadeType.REMOVE)
	private Step step;
	@Column(nullable = false, length = 1000)
	private String description;
	@Enumerated(EnumType.STRING)
	private LangCode langCode;

	public StepDescription() {
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LangCode getLangCode() {
		return langCode;
	}

	public void setLangCode(LangCode langCode) {
		this.langCode = langCode;
	}

}
