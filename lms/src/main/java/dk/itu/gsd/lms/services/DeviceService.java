package dk.itu.gsd.lms.services;

public interface DeviceService {
	public boolean toggleLight(String deviceId, float turnOn);
	public boolean hasRegistreredActivity(String deviceId, String type, int minutes);

}
