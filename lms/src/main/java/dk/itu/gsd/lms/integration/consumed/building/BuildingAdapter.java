package dk.itu.gsd.lms.integration.consumed.building;

import dk.itu.gsd.lms.model.Building;

public interface BuildingAdapter {

	public String getBuildingInfo();
	public Building getBuildingDescription(Long bid);

}
