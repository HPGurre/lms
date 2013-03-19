package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
import dk.itu.gsd.lms.services.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomAdapter roomAdapter;
	
	@Autowired
	RoomDao roomDao;
	
	@Override
	public String getEnergyUsageByDay(Long foreignRoomId) {
		// TODO get list of measurement agregate the data?
		roomAdapter.getDeviceEnergyUsageByDay(foreignRoomId, "ID-1");
		roomAdapter.getDeviceEnergyUsageByDay(foreignRoomId, "ID-2");
		
		return null;
	}

	@Override
	public String getEnergyUsageByWeek(Long foreignRoomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnergyUsageByMonth(Long foreignRoomId) {
		// TODO Auto-generated method stub
		return null;
	}

}
