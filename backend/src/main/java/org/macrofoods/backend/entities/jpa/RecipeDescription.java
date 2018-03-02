package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecipeDescription {
	@Id
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
}
