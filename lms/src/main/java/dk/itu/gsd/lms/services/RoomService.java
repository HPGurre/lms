package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.AbstractRoom;

public interface RoomService {

	// Service method towards the simulator
	public Float getEnergyUsageByDay(AbstractRoom room);
	public Float getEnergyUsageByWeek(Long foreignRoomId);
	public Float getEnergyUsageByMonth(Long foreignRoomId);

	// Service method for serving content to android
	public AbstractRoom getRoomData(Long RoomId);
	
}
