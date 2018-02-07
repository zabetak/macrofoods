package org.macrofoods.backend.entities;

public final class Food {
	private final int id;
	private final FoodGroup fdGroup;
	private final boolean isSurvey;
	private final short refuse;
	private final float nFactor;
	private final float protFactor;
	private final float fatFactor;
	private final float choFactor;

	public Food(int id, FoodGroup fdGroup, boolean isSurvey, short refuse, float nFactor, float protFactor,
			float fatFactor, float choFactor) {
		super();
		this.id = id;
		this.fdGroup = fdGroup;
		this.isSurvey = isSurvey;
		this.refuse = refuse;
		this.nFactor = nFactor;
		this.protFactor = protFactor;
		this.fatFactor = fatFactor;
		this.choFactor = choFactor;
	}

}
