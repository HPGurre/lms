package dk.itu.gsd.lms.integration.consumed.building;

import java.util.Calendar;
import java.util.List;

import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;

public interface DeviceAdapter {
	
	
	public boolean turnOffLight(String deviceId);
	public boolean adjustLight(String deviceId, double gain);
	
	public MeasurementDto getLatestDeviceMeasurement(String deviceId, String type);
	
	public List<MeasurementDto> getLatestDeviceMeasurements(String deviceId, String type, int noOfMeasurements);

	/**
	 *  Get all the measurements for a certain device for a specified time period
	 */
	public List<MeasurementDto> getDeviceEnergyUsageByPeriod(String deviceId,
			String type, Calendar startDate, Calendar endDate);
	
	/**
	 *  Get all the measurements for a certain device for a specified time period. 
	 *  This method can limit the amount of retrieved measurements.
	 */
	public List<MeasurementDto> getDeviceEnergyUsageByNumber(String deviceId,
			String type, Calendar startDate, int noOfMeasurements);

}
