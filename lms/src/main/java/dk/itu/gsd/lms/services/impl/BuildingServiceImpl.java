package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.services.BuildingService;

@Service("buildingService")
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	BuildingAdapter buildingAdapter;

	@Override
	public String getBuildings() {
		//TODO Does anything else need to be done here?
		return buildingAdapter.getBuildings();
	}

	@Override
	public Building getBuildingDetails(int id) {
		// TODO Auto-generated method stub
		return new Building();
	}
	
	

}
