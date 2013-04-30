package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.integration.consumed.building.FloorAdapter;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Floor;
import dk.itu.gsd.lms.services.FloorService;

@Service("floorService")
public class FloorServiceImpl implements FloorService {
	@Autowired
	FloorAdapter floorAdapter;

	@Autowired
	FloorDao floorDao;

	@Override
	public Float getEnergyUsageByDay(Long floorID) {
		Float result = 0F;
		Floor floor = floorDao.find(floorID);
		for (AbstractRoom room : floor.getRooms()) {
			result += room.getEnergyUsageLastDay();		
		}		
		return result;
	}

	@Override
	public Float getEnergyUsageByWeek(Long floorID) {
		Float result = 0F;
		Floor floor = floorDao.find(floorID);
		for (AbstractRoom room : floor.getRooms()) {
			result += room.getEnergyUsageLastWeek();		
		}		
		return result;
	}

	@Override
	public Float getEnergyUsageByMonth(Long floorID) {
		Float result = 0F;
		Floor floor = floorDao.find(floorID);
		for (AbstractRoom room : floor.getRooms()) {
			result += room.getEnergyUsageLastMonth();		
		}		
		return result;
	}

	@Override
	public Floor getFloorData(Long roomId) {
		return floorDao.find(roomId);
	}

}
