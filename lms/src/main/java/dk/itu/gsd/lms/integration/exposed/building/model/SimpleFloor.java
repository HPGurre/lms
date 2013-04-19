package dk.itu.gsd.lms.integration.exposed.building.model;

import java.util.List;

public class SimpleFloor {
	private Long floorId ;
	private List<SimpleRoom> rooms;

	public List<SimpleRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<SimpleRoom> rooms) {
		this.rooms = rooms;
	}

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}
	
}
