package dk.itu.gsd.lms.integration.consumed.building;

import java.util.Calendar;
import java.util.List;

import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;
import dk.itu.gsd.lms.model.AbstractRoom;


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
	
	/**
	 *  Get all the measurements for a certain device for this month
	 */
	public List<MeasurementDto> getDeviceEnergyUsageByPeriod(String deviceId,
			String type, Calendar startDate, Calendar endDate);
	
	public List<MeasurementDto> getDeviceEnergyUsageByNumber(String deviceId,
			String type, Calendar startDate, int noOfMeasurements);
	


}
