package org.macrofoods.backend.dto;

public final class FoodDTO {
	private int id;
	private String description;
	private String category;
	private MacrosDTO macros;

	public FoodDTO() {

	}

	public FoodDTO(int id, String description, String category, MacrosDTO macros) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.macros = macros;
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

	public void setMacros(MacrosDTO macros) {
		this.macros = macros;
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

	public MacrosDTO getMacros() {
		return macros;
	}
}
