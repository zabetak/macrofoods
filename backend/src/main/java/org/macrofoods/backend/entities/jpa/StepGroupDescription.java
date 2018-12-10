package org.macrofoods.backend.entities.jpa;

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
public class StepGroupDescription {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgdescription_generator")
	@SequenceGenerator(name = "sgdescription_generator", sequenceName = "step_group_description_seq", allocationSize = 10)
	private Integer id;
	@ManyToOne(optional = false)
	private StepGroup group;
	@Column(length = 60)
	private String title;
	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private LangCode langCode;

	public StepGroupDescription() {
	}

	public StepGroup getGroup() {
		return group;
	}

	public void setGroup(StepGroup group) {
		this.group = group;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LangCode getLangCode() {
		return langCode;
	}

	public void setLangCode(LangCode langCode) {
		this.langCode = langCode;
	}

}
