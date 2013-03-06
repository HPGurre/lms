package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;

@Component("buildingAdapter")
public class BuildingAdapterImpl implements BuildingAdapter {
	

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://gsd.itu.dk/api/user/buildinginfo/6/").build();
	}
	public String getBuildings() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		 return service.accept(MediaType.APPLICATION_JSON).get(String.class)
		.toString();
	}
}
