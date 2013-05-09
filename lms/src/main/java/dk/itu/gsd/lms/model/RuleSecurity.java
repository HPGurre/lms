package dk.itu.gsd.lms.model;

public class RuleSecurity {

	private Boolean isLightAllowed;
	private String RoomId;
	private String timeRange;
	public Boolean getIsLightAllowed() {
		return isLightAllowed;
	}
	public String getRoomId() {
		return RoomId;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setIsLightAllowed(Boolean isLightAllowed) {
		this.isLightAllowed = isLightAllowed;
	}
	public void setRoomId(String roomId) {
		RoomId = roomId;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
}
