package dk.itu.gsd.lms.model;

import java.util.List;

public abstract class Room{
	private SecurityMode securityMode;
	private List<Room> adjecentRooms;
	
	private Long foreignRoomID;

}
