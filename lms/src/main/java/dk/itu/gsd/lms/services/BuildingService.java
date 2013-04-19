package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.Building;


public interface BuildingService {
	public String getBuildingInfo();
	public Building getBuildingDescription(Long bid);
	
	public Building getBuilding(Long bid);
}
