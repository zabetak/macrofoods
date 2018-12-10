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
public class RecipeDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rdescription_generator")
	@SequenceGenerator(name = "rdescription_generator", sequenceName = "recipe_description_seq", allocationSize = 10)
	private Integer id;
	@ManyToOne(optional = false)
	private Recipe recipe;
	@Column(nullable = false, length = 200)
	private String title;
	@Column(length = 1000)
	private String summary;
	@Column(length = 1000)
	private String conclusion;
	@Enumerated(EnumType.STRING)
	private LangCode langCode;

	public RecipeDescription() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public LangCode getLangCode() {
		return langCode;
	}

	public void setLangCode(LangCode langCode) {
		this.langCode = langCode;
	}
}
