package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.Floor;

public interface FloorService {
	public Float getEnergyUsageByDay(Long floorID);
	public Float getEnergyUsageByWeek(Long floorID);
	public Float getEnergyUsageByMonth(Long floorID);
	
	
	
	// Service method for serving content to android
	public Floor getFloorData(Long floorId);
	
	

}
