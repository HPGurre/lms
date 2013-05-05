package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.integration.consumed.building.DeviceAdapter;
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
}
