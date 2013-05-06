package dk.itu.gsd.lms.integration.consumed.building;

import java.util.List;

import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;

public interface DeviceAdapter {
	public boolean toggleLight(String deviceId, float gain);
	public List<MeasurementDto> getLatestDeviceMeasurements(String deviceId, String type, int noOfMeasurements);
}
