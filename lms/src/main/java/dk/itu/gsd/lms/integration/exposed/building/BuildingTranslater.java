package dk.itu.gsd.lms.integration.exposed.building;

import java.util.ArrayList;
import java.util.List;

import dk.itu.gsd.lms.integration.exposed.building.model.SimpleFloor;
import dk.itu.gsd.lms.integration.exposed.building.model.SimpleRoom;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.model.EnergyState;
import dk.itu.gsd.lms.model.Floor;

public class BuildingTranslater{

	public BuildingDto translate(Building building) {
		BuildingDto dto = new BuildingDto();
		
		dto.setDailyEnergyUsage(building.getEnergyUsageLastDay());
		dto.setWeeklyEnergyUsage(building.getEnergyUsageLastWeek());
		dto.setMonthlyEnergyUsage(building.getEnergyUsageLastMonth());
		dto.setTotalEnergyUsage(1000F);
		dto.setMaxEnergyUsage(1000F);
		dto.setEnergyState(EnergyState.NORMAL);
		return dto;
	}

	public BuildingBluePrintDto translateBuildingToBlueprint(Building building) {
		BuildingBluePrintDto dto = new BuildingBluePrintDto();
		dto.setBuildingNo(building.getId());
		
		List<SimpleFloor> floors = new ArrayList<SimpleFloor>();
		for (Floor floor : building.getFloors()) {
			SimpleFloor sf = new SimpleFloor();
			sf.setFloorId(floor.getId());
			
			List<SimpleRoom> rooms = new ArrayList<SimpleRoom>();
			for (AbstractRoom room : floor.getRooms()) {
				SimpleRoom sr = new SimpleRoom();
				sr.setRoomID(room.getId());
				rooms.add(sr);
				
			}
			sf.setRooms(rooms);
			
			floors.add(sf);
		}
		dto.setFloors(floors);
		
		
		return dto;
	}
}
