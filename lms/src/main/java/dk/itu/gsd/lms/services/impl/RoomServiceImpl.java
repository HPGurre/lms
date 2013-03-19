package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
import dk.itu.gsd.lms.services.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomAdapter roomAdapter;
	
	@Override
	public String getEnergyUsageByDay(Long foreignRoomId) {
		// TODO Auto-generated method stub
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
