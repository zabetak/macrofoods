package org.macrofoods.backend.dto;

import java.util.List;

public class StepGroupDTO {
	private String name;
	private List<StepDTO> steps;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StepDTO> getSteps() {
		return steps;
	}

	public void setSteps(List<StepDTO> steps) {
		this.steps = steps;
	}
}
