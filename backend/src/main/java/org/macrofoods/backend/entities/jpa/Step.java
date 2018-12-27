package org.macrofoods.backend.entities.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Step {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "step_generator")
	@SequenceGenerator(name = "step_generator", sequenceName = "step_seq", allocationSize = 10)
	private Integer id;
	@Column(nullable = false)
	private Short seq;
	@ManyToOne(optional = false)
	private StepGroup group;
	@Column(nullable = true)
	private Integer time;
	@OneToMany(mappedBy = "step", targetEntity = StepDescription.class)
	private List<StepDescription> descriptions;

	public Step() {
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public StepGroup getGroup() {
		return group;
	}

	public void setGroup(StepGroup group) {
		this.group = group;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public List<StepDescription> getDescriptions() {
		return descriptions;
	}
}
