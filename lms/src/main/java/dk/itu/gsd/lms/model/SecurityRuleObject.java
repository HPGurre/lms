package dk.itu.gsd.lms.model;

public class SecurityRuleObject {

	private String RoomId;
	private String timeRange;
	private Boolean isLightAllowed;
	public String getRoomId() {
		return RoomId;
	}
	public void setRoomId(String roomId) {
		RoomId = roomId;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	public Boolean getIsLightAllowed() {
		return isLightAllowed;
	}
	public void setIsLightAllowed(Boolean isLightAllowed) {
		this.isLightAllowed = isLightAllowed;
	}
}
