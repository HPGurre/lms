package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.AbstractRoom;

public interface RoomService {

	// Service method towards the simulator
	public Float getEnergyUsageByDay(AbstractRoom room);
	public String getEnergyUsageByWeek(Long foreignRoomId);
	public String getEnergyUsageByMonth(Long foreignRoomId);

	// Service method for serving content to android
	public AbstractRoom getRoomData(Long RoomId);

	// Scheduled method
	public void getEnergymeasurements();
	
}
