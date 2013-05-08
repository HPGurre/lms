package dk.itu.gsd.lms.model;

public class RuleSchedule {

	private String day;
	private String roomType;
	private int timeoutLimit;	
	private String timeRange;
	
	public String getDay() {
		return day;
	}
	public String getRoomType() {
		return roomType;
	}
	public int getTimeoutLimit() {
		return timeoutLimit;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public void setTimeoutLimit(int timeoutLimit) {
		this.timeoutLimit = timeoutLimit;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	
}
