package dk.itu.gsd.lms.model;

public enum TimeRangeLabel {
	DAY("Day"),
	EVENING("Evening"), 
	NIGHT("Night"),
	WEEKEND("Weekend"),
	HOLIDAY("Holiday");

	private String description;

	TimeRangeLabel(String description){
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
