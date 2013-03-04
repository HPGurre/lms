package dk.itu.gsd.lms.model;

public enum EnergyState {
	ABUNDANT("Abundant", "Energy is overflowing", 98, 100),
	NORMAL("Normal", "Normal energy levels", 40, 98), 
	LIMITED("Limited", "Limited amount of energy", 10, 39),
	EXHUASTED("Exhausted", "Energy levels are almost depleeted", 1,9),
	DEAD("DEAD","No energy is available", 0,1);
	
	private final int upperBound;
	private final int lowerBound;
	private String description;
	private String displayName;

	EnergyState(String displayName, String description, int lowerBound, int upperBound){
		this.displayName = displayName;
		this.description = description;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDescription() {
		return description;
	}

}
