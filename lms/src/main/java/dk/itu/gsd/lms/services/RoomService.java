package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.AbstractRoom;

public interface RoomService {
		
		//Service method towards the simulator
		String getEnergyUsageByDay(Long foreignRoomId);
		String getEnergyUsageByWeek(Long foreignRoomId);
		String getEnergyUsageByMonth(Long foreignRoomId);
		
		//Service method for serving content to android
		AbstractRoom getRoomData(Long RoomId);

		//Scheduled method
		void getEnergymeasurements();
}
