package dk.itu.gsd.lms.model;


public enum SecurityMode {
	ALLOWED ("Light Allowed", "Devices are handled."),
	DISALLOWED ("Light Disallowed", "Devices are allowed. All events are unhandled.");
	//ALERT("Light Alert", "Notification for devices occur send to Alarm center");
	
	private String description;
	private String modeName;

	SecurityMode(String modeName, String description){
		this.modeName = modeName;
		this.description = description;
		
	}

	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName= modeName;
	}

	public String getDescription() {
		return description;
	}
}
