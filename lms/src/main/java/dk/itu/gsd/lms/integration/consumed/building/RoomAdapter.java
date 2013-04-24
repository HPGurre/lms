package dk.itu.gsd.lms.integration.consumed.building;

import java.util.List;

import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;


public interface RoomAdapter {
	/**
	 *  Get all the measurements for a certain device for today
	 */
	public List<MeasurementDto> getDeviceEnergyUsageByDay(String deviceId, String type);
	/**
	 *  Get all the measurements for a certain device for the last 7 days.
	 */
	public void getDeviceEnergyUsageByWeek(Long roomId, String deviceId);
	/**
	 *  Get all the measurements for a certain device for this month
	 */
	public void getDeviceEnergyUsageByMonth(Long roomId, String deviceId);

}
