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
public class IngredientGroupDescription {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "igdescription_generator")
	@SequenceGenerator(name = "igdescription_generator", sequenceName = "ingredient_group_description_seq", allocationSize = 10)
	private Integer id;
	@ManyToOne(optional = false)
	private IngredientGroup group;
	@Column(length = 60)
	private String title;
	@Enumerated(EnumType.STRING)
	private LangCode langCode;

	public IngredientGroupDescription() {
	}

	public IngredientGroup getGroup() {
		return group;
	}

	public void setGroup(IngredientGroup group) {
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
