package dk.itu.gsd.lms.integration.exposed.building;

import dk.itu.gsd.lms.model.Building;

public class BuildingTranslater{

	BuildingDto translate(Building building) {
		BuildingDto dto = new BuildingDto();
		
		dto.setEnergyConsumptionValue(10l);
		return dto;
	}
}
