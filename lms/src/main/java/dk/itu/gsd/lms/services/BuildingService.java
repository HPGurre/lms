package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.model.Floor;


public interface BuildingService {
	public String getBuildingInfo();
	public Building getBuildingDescription(Long bid);
	public Building getBuilding(Long bid);
	public Float getEnergyUsageByDay(Long bid);
	public Float getEnergyUsageByWeek(Long bid);
	public Float getEnergyUsageByMonth(Long bid);
	
	
	// Service method for serving content to android
	public Building getBuildingData(Long buildingId);
}
