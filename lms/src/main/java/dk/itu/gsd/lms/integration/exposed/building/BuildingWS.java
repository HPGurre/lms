package dk.itu.gsd.lms.integration.exposed.building;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		return buildingService.getBuildingInfo();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/details/{id}")
	public BuildingDto getBuildingdetails(@PathParam("id") Long id ) {
		return new BuildingTranslater().translate(buildingService.getBuildingDescription(id));
	}
}