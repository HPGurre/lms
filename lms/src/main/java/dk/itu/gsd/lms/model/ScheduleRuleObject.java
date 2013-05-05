package dk.itu.gsd.lms.model;

public class ScheduleRuleObject {

	private String day;
	private String timeRange;
	private String roomType;	
	private int timeoutLimit;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getTimeoutLimit() {
		return timeoutLimit;
	}
	public void setTimeoutLimit(int timeoutLimit) {
		this.timeoutLimit = timeoutLimit;
	}
	
}
