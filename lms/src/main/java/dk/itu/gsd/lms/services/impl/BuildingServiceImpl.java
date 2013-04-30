package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.model.Floor;
import dk.itu.gsd.lms.services.BuildingService;

@Service("buildingService")
public class BuildingServiceImpl implements BuildingService {

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
			for (AbstractRoom room : floor.getRooms()) {
				result += room.getEnergyUsageLastDay();		
			}	
		}
		return result;
	}

	@Override
	public Float getEnergyUsageByWeek(Long bid) {
		Float result = 0F;
		Building building = buildingDao.find(bid);
		for (Floor floor : building.getFloors()) {
			for (AbstractRoom room : floor.getRooms()) {
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
			for (AbstractRoom room : floor.getRooms()) {
				result += room.getEnergyUsageLastMonth();		
			}	
		}
		return result;
	}

	@Override
	public Building getBuildingData(Long roomId) {
		return buildingDao.find(roomId);
	}
}
