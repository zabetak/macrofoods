package org.macrofoods.backend.entities.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_generator")
	@SequenceGenerator(name = "recipe_generator", sequenceName = "recipe_seq", allocationSize = 10)
	private Integer id;
	@Column
	private Integer views;
	@Column
	private Integer likes;
	@Column
	private Short score;
	@Column
	private Short prepTime;
	@Column
	private Short cookTime;
	@Enumerated(EnumType.ORDINAL)
	private Difficulty difficulty;
	@Column
	private Short servings;
	@Column(nullable = false)
	private byte[] image;
	@OneToMany(mappedBy = "recipe", targetEntity = RecipeDescription.class)
	private List<RecipeDescription> descriptions;
	@OneToMany(mappedBy = "recipe", targetEntity = IngredientGroup.class)
	private List<IngredientGroup> ingGroups;
	@ManyToMany
	@JoinTable(name = "RecipeTags", joinColumns = { @JoinColumn(name = "recipe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_id") })
	private List<Tag> tags = new ArrayList<Tag>();

	public Recipe() {
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public void setPrepTime(Short prepTime) {
		this.prepTime = prepTime;
	}

	public void setCookTime(Short cookTime) {
		this.cookTime = cookTime;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setServings(Short servings) {
		this.servings = servings;
	}

	public Integer getId() {
		return id;
	}

	public Short getScore() {
		return score;
	}

	public Short getPrepTime() {
		return prepTime;
	}

	public Short getCookTime() {
		return cookTime;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public Short getServings() {
		return servings;
	}

	public List<RecipeDescription> getDescriptions() {
		return descriptions;
	}

	public List<IngredientGroup> getIngGroups() {
		return ingGroups;
	}

	public List<Tag> getTags() {
		return tags;
	}
}
