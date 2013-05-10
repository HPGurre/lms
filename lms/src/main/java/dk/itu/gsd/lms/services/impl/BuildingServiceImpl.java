package dk.itu.gsd.lms.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;
import dk.itu.gsd.lms.model.Room;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.model.Floor;
import dk.itu.gsd.lms.services.BuildingService;
import dk.itu.gsd.lms.services.RoomService;

@Service("buildingService")
public class BuildingServiceImpl implements BuildingService {
	private static Logger logger = Logger.getLogger(BuildingServiceImpl.class);

	@Autowired
	BuildingAdapter buildingAdapter;

	@Autowired
	BuildingDao buildingDao;

	@Override
	public String getBuildingInfo() {
		return buildingAdapter.getBuildingInfo();
	}

	@Override
	public Building getBuildingDescription(Long bid) {
		return buildingAdapter.getBuildingDescription(bid);
	}

	@Override
	public Building getBuilding(Long bid) {
		return buildingDao.find(bid);
	}
	
	@Override
	public Float getEnergyUsageByDay(Long bid) {
		Float result = 0F;
		Building building = buildingDao.find(bid);
		for (Floor floor : building.getFloors()) {
			logger.debug("Floor is: "+result);
			for (Room room : floor.getRooms()) {
				result += room.getEnergyUsageLastDay();	
				logger.debug("Room is: "+result);
			}	
		}
		logger.debug("Result is: "+result);
		return result;
	}

	@Override
	public Float getEnergyUsageByWeek(Long bid) {
		Float result = 0F;
		Building building = buildingDao.find(bid);
		for (Floor floor : building.getFloors()) {
			for (Room room : floor.getRooms()) {
				result += room.getEnergyUsageLastWeek();		
			}	
		}
		return result;
	}

	@Override
	public Float getEnergyUsageByMonth(Long bid) {
		Float result = 0F;
		Building building = buildingDao.find(bid);
		for (Floor floor : building.getFloors()) {
			for (Room room : floor.getRooms()) {
				result += room.getEnergyUsageLastMonth();		
			}	
		}
		return result;
	}

	@Override
	public Building getBuildingData(Long roomId) {
		return buildingDao.find(roomId);
	}

	@Override
	public void updateBuildingMeasurementData() {
		for (Building building : buildingDao.findAll()) {
			building.setEnergyUsageLastDay(getEnergyUsageByDay(building.getId()));
			building.setEnergyUsageLastWeek(getEnergyUsageByWeek(building.getId()));
			building.setEnergyUsageLastMonth(getEnergyUsageByMonth(building.getId()));
			buildingDao.save(building);
			logger.debug("Building measurements has been updated");
			logger.debug("Energy usage by day " + getEnergyUsageByDay(building.getId()));
		}
	}
}
