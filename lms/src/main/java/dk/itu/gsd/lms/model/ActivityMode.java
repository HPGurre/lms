package dk.itu.gsd.lms.model;

public enum ActivityMode {
	BATHROOM_MODE("Bathroom"), 
	HALLWAY_MODE("Hallway"),
	LECTURE_MODE("Lecture"),
	LOUNGE_MODE("Lounge"),
	OFFICE_MODE("Office"),
	PARTY_MODE("Party"),
	VISUAL_INTENSIVE_MODE("Visual intensive work");
	
	private String displayName;
	
	ActivityMode(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
