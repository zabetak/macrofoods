package org.macrofoods.backend.entities.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_generator")
	@SequenceGenerator(name = "tag_generator", sequenceName = "tag_seq", allocationSize = 10)
	private Integer id;
	@OneToMany(mappedBy = "tag", targetEntity = TagDescription.class)
	private List<TagDescription> descriptions = new ArrayList<TagDescription>();
	@ManyToMany(mappedBy = "tags")
	private List<Recipe> recipes = new ArrayList<Recipe>();

	public Integer getId() {
		return id;
	}

	public void setDescriptions(List<TagDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public List<TagDescription> getDescriptions() {
		return descriptions;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}
}
