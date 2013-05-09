package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.Room;

public interface RoomService {

	// Service method towards the simulator
	public Float getEnergyUsageByDay(Room room);
	public Float getEnergyUsageByWeek(Room room);
	public Float getEnergyUsageByMonth(Room room);
	
//	TODO Remove these?
//	public int getActivityLevel(AbstractRoom room);
//	public float getLampMinPower(AbstractRoom room);
//	public void setLightsAccordingToPolicy(AbstractRoom room);
	
	// Service method for serving content to android
	public Room getRoomData(Long RoomId);
	
	//Human comfort
	public void adjustLight();
	public void updateRoomMeasurementdata();
	public void turnOffLight();
	
	
	
}
