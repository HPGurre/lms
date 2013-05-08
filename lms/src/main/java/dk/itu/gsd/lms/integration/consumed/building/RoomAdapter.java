package dk.itu.gsd.lms.integration.consumed.building;

import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;


public interface RoomAdapter {
	
	/**
	 *  Get all the light measurements for a certain room for a specified time period. 
	 */
	public MeasurementDto getCurrentRoomLight(String roomId);
	
}
