package dk.itu.gsd.lms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.integration.consumed.building.DeviceAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;
import dk.itu.gsd.lms.services.DeviceService;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{
	@Autowired
	private DeviceAdapter deviceAdapter;
		
	public boolean toggleLight(String deviceId, float gain){
		if(!deviceId.contains("light")){
			throw new IllegalArgumentException("The Device is not a Light");
		}			
		return deviceAdapter.toggleLight(deviceId, gain);
	}

	@Override
	public boolean hasRegistreredActivity(String deviceId, String type, int minutes) {
		List<MeasurementDto> measurements = deviceAdapter.getLatestDeviceMeasurements(deviceId, type, minutes);
		if (measurements.isEmpty()) {
			return false;
		}
		String firstValue = measurements.get(0).getValue();
		for (MeasurementDto measurementDto : measurements) {
			if (!firstValue.equalsIgnoreCase(measurementDto.getValue())) {
				return true;
			}
		}	
		return false;
	}
}
