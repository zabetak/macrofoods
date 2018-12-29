package org.macrofoods.backend.dto;

public final class FoodDTO {
	private int id;
	private String description;
	private String category;
	private NutritionalFactsDTO facts;

	public FoodDTO() {

	}

	public FoodDTO(int id, String description, String category, NutritionalFactsDTO nutrients) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.facts = nutrients;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public NutritionalFactsDTO getFacts() {
		return facts;
	}

	public void setFacts(NutritionalFactsDTO facts) {
		this.facts = facts;
	}

}
