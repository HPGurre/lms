package dk.itu.gsd.lms.integration.exposed.floor;

import dk.itu.gsd.lms.integration.exposed.room.RoomDto;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.EnergyState;
import dk.itu.gsd.lms.model.Floor;

/**
 * 
 * @author HP
 * translate (or insert) values from the stored object into the exposed dto.
 */
public class FloorTranslater {

	
	public FloorDto translate(Floor floor) {
		FloorDto dto = new FloorDto();
		
		dto.setDailyEnergyUsage(floor.getEnergyUsageLastDay());
		dto.setWeeklyEnergyUsage(floor.getEnergyUsageLastWeek());
		dto.setMonthlyEnergyUsage(floor.getEnergyUsageLastMonth());
		dto.setTotalEnergyUsage(1000F);
		dto.setMaxEnergyUsage(1000F);
		dto.setEnergyState(EnergyState.NORMAL);
		
		return dto;
	}
}
