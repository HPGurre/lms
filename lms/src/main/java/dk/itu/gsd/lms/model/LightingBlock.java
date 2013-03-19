package dk.itu.gsd.lms.model;

import java.util.List;

public class LightingBlock {

	private List<AbstractRoom> rooms;
	private List<Floor> floors;
	private Building building;

	public LightingBlock(List<AbstractRoom> rooms, List<Floor> floors, Building building) {
		if (false/* Rooms are not adjacent */){//TODO Make the check
			throw new IllegalArgumentException("Rooms are not adjacent");
		}
		if (false/* Rooms are not adjacent */){//TODO make the check
			throw new IllegalArgumentException("Floors are not adjacent");
		}
		this.rooms = rooms;
		this.floors = floors;
		this.building = building;
	}
}
