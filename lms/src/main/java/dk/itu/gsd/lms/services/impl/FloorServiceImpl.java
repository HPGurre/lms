package dk.itu.gsd.lms.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.model.Room;
import dk.itu.gsd.lms.model.Floor;
import dk.itu.gsd.lms.services.FloorService;

@Service("floorService")
public class FloorServiceImpl implements FloorService {
	private static Logger logger = Logger.getLogger(FloorServiceImpl.class);

	@Autowired
	FloorDao floorDao;

	@Override
	public Float getEnergyUsageByDay(Long floorID) {
		Float result = 0F;
		Floor floor = floorDao.find(floorID);
		for (Room room : floor.getRooms()) {
			result += room.getEnergyUsageLastDay();
		}
		return result;
	}

	@Override
	public Float getEnergyUsageByWeek(Long floorID) {
		Float result = 0F;
		Floor floor = floorDao.find(floorID);
		for (Room room : floor.getRooms()) {
			result += room.getEnergyUsageLastWeek();
		}
		return result;
	}

	@Override
	public Float getEnergyUsageByMonth(Long floorID) {
		Float result = 0F;
		Floor floor = floorDao.find(floorID);
		for (Room room : floor.getRooms()) {
			result += room.getEnergyUsageLastMonth();
		}
		return result;
	}
	
	@Override
	public void updateFloorMeasurementData() {
		for (Floor floor : floorDao.findAll()) {
			floor.setEnergyUsageLastDay(getEnergyUsageByDay(floor.getId()));
			floor.setEnergyUsageLastWeek(getEnergyUsageByWeek(floor.getId()));
			floor.setEnergyUsageLastMonth(getEnergyUsageByMonth(floor.getId()));
			floorDao.save(floor);
			logger.debug("Floor measurements has been updated");
		}
	}

	@Override
	public Floor getFloorData(Long roomId) {
		return floorDao.find(roomId);
	}

}
