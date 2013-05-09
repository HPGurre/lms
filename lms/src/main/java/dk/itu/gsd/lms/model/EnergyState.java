package dk.itu.gsd.lms.model;

public enum EnergyState {
	ABUNDANT("Abundant", "Energy is overflowing", 98, 100),
	DEAD("DEAD","No energy is available", 0,1), 
	EXHUASTED("Exhausted", "Energy levels are almost depleeted", 1,9),
	LIMITED("Limited", "Limited amount of energy", 10, 39),
	NORMAL("Normal", "Normal energy levels", 40, 98);
	
	private String description;
	private String displayName;
	private final int lowerBound;
	private final int upperBound;

	EnergyState(String displayName, String description, int lowerBound, int upperBound){
		this.displayName = displayName;
		this.description = description;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public String getDescription() {
		return description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
