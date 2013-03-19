package dk.itu.gsd.lms.services;

public interface RoomService {
		String getEnergyUsageByDay(Long foreignRoomId);
		String getEnergyUsageByWeek(Long foreignRoomId);
		String getEnergyUsageByMonth(Long foreignRoomId);

}
