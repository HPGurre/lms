package dk.itu.gsd.lms.integration.exposed.room;

import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.EnergyState;
/**
 * 
 * @author HP
 * translate (or insert) values from the stores object into the exposed dto.
 */
public class RoomTranslater {

	public RoomDto translate(AbstractRoom room) {
		RoomDto dto = new RoomDto();
		
		
		dto.setDailyEnergyUsage(room.getEnergyUsageLastDay());
		dto.setWeeklyEnergyUsage(room.getEnergyUsageLastWeek());
		dto.setMonthlyEnergyUsage(room.getEnergyUsageLastMonth());
		dto.setTotalEnergyUsage(1000F);
		dto.setMaxEnergyUsage(1000F);
		dto.setEnergyState(EnergyState.NORMAL);
		
		
		
		return dto;
	}
	
}
