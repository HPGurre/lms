package dk.itu.gsd.lms.services;

public interface UpdateService {
	
	// Scheduled methods
	public void initiateMeasurementUpdate();
	
	public void initiateLightTurnOff();
	
	public void initiateLightAdjustment();
}
