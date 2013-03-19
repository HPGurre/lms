package dk.itu.gsd.lms.integration.consumed.building;


public interface RoomAdapter {
	/**
	 *  Get all the measurements for a certain device for today
	 */
	public void getDeviceEnergyUsageByDay(Long roomId, String deviceId);
	/**
	 *  Get all the measurements for a certain device for the last 7 days.
	 */
	public void getDeviceEnergyUsageByWeek(Long roomId, String deviceId);
	/**
	 *  Get all the measurements for a certain device for this month
	 */
	public void getDeviceEnergyUsageByMonth(Long roomId, String deviceId);

}
