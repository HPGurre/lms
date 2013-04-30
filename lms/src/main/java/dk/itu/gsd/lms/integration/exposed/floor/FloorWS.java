package dk.itu.gsd.lms.integration.exposed.floor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.itu.gsd.lms.services.FloorService;

@Path("/floor")
@Component
public class FloorWS {
	
	@Autowired
	private FloorService floorService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public FloorDto getFloorData(@PathParam("id") Long id ) {
		return new FloorTranslater().translate(floorService.getFloorData(id));
	}

}
