package dk.itu.gsd.lms.model;

public enum RoomActivity {
	OFFICE_WORK("office work"), 
	PROGRAMMING("Programming"),
	TEACHING("Teaching"),
	LOUNGE("Lounge"),
	PARTY("Party");
	
	private String description;
	
	RoomActivity(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
