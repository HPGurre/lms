package dk.itu.gsd.lms.integration.exposed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.itu.gsd.lms.services.PingService;

@Path("/ping")
@Component
public class PingWS {

	@Autowired
	private PingService pingService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String ping() {
		return pingService.ping();
	}
}
