package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.services.BuildingService;

@Service("buildingService")
public class BuildingServiceImpl implements BuildingService{
	
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
	
	

}
