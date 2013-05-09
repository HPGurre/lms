package dk.itu.gsd.lms.services;

public interface DeviceService {
	public boolean turnOffLight(String deviceId);
	public boolean adjustLight(String deviceId, double gain);
	public boolean hasRegistreredActivity(String deviceId, String type, int minutes);
}
