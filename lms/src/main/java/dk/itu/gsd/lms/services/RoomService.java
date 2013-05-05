package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.AbstractRoom;

public interface RoomService {

	// Service method towards the simulator
	public Float getEnergyUsageByDay(AbstractRoom room);
	public Float getEnergyUsageByWeek(AbstractRoom room);
	public Float getEnergyUsageByMonth(AbstractRoom room);
	public int getActivityLevel(AbstractRoom room);
	public float getLampMinPower(AbstractRoom room);
	public void setLightsAccordingToPolicy(AbstractRoom room);
	
	// Service method for serving content to android
	public AbstractRoom getRoomData(Long RoomId);
	
}
