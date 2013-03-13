package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.Building;


public interface BuildingService {
	String getBuildings();

	Building getBuildingDetails(int id);
}
