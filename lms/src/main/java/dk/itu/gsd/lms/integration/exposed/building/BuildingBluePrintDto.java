package dk.itu.gsd.lms.integration.exposed.building;

import java.util.List;

import dk.itu.gsd.lms.integration.exposed.building.model.SimpleFloor;


public class BuildingBluePrintDto {

	private Long buildingNo;
	private List<SimpleFloor> floors;

	public List<SimpleFloor> getFloors() {
		return floors;
	}

	public void setFloors(List<SimpleFloor> floors) {
		this.floors = floors;
	}

	public Long getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(Long buildingNo) {
		this.buildingNo = buildingNo;
	}

//	public class Floor {
//		private Long floorNo;
//		private List<Room> floors;
//
//		public List<Room> getFloors() {
//			return floors;
//		}
//
//		public void setFloors(List<Room> floors) {
//			this.floors = floors;
//		}
//
//		public Long getFloorNo() {
//			return floorNo;
//		}
//
//		public void setFloorNo(Long floorNo) {
//			floorNo = floorNo;
//		}
//	}
//
//	private class Room {
//		private Long roomNo;
//
//		public Long getRoomNo() {
//			return roomNo;
//		}
//
//		public void setRoomNo(Long roomNo) {
//			this.roomNo = roomNo;
//		}
//	}

}
