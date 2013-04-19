package dk.itu.gsd.lms.integration.exposed.room;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.itu.gsd.lms.services.RoomService;


@Path("/room")
@Component
public class RoomWS {

	@Autowired
	private RoomService roomService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public RoomDto getRoomData(@PathParam("id") Long id ) {
		return new RoomTranslater().translate(roomService.getRoomData(id));
	}
}
