package dk.itu.gsd.lms.model;

public enum ActivityMode {
	VISUAL_INTENSIVE_MODE("Visual intensive work"), 
	OFFICE_MODE("Office"),
	LECTURE_MODE("Lecture"),
	HALLWAY_MODE("Hallway"),
	BATHROOM_MODE("Bathroom"),
	LOUNGE_MODE("Lounge"),
	PARTY_MODE("Party");
	
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
