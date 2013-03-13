package dk.itu.gsd.lms.integration.consumed.building.impl;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;
import dk.itu.gsd.lms.model.Building;

@Component("buildingAdapter")
public class BuildingAdapterImpl extends AbstractAdapter implements
		BuildingAdapter {

	public String getBuildings() {
		return service.accept(MediaType.APPLICATION_JSON).get(String.class).toString();
	}

	@Override
	public Building getBuildingDetails() {
		
		return null;
	}
}
