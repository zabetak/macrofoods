package org.macrofoods.backend.entities.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class StepGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgroup_generator")
	@SequenceGenerator(name = "sgroup_generator", sequenceName = "step_group_seq", allocationSize = 10)
	private Integer id;
	@Column(nullable = false)
	private Short seq;
	@ManyToOne(optional = false)
	private Recipe recipe;
	@OneToMany(mappedBy = "group", targetEntity = StepGroupDescription.class, cascade = CascadeType.REMOVE)
	private List<StepGroupDescription> descriptions;
	@OneToMany(mappedBy = "group", targetEntity = Step.class, cascade = CascadeType.REMOVE)
	private List<Step> steps;

	public StepGroup() {
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public List<StepGroupDescription> getDescriptions() {
		return descriptions;
	}

	public List<Step> getSteps() {
		return steps;
	}

}
