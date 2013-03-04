package dk.itu.gsd.lms.integration.exposed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.itu.gsd.lms.services.BuildingService;

@Path("/building")
@Component
public class BuildingWS {

	@Autowired
	private BuildingService buildingService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBuildings() {
		return buildingService.getBuildings();
	}
}
