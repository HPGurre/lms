package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractAdapter {
	protected static String SERVER_ADDRESS = "http://gsd.itu.dk/api/user/";
	protected static String LIGHTING_BID = "5";
	
	protected static String MEASUREMENT_PATH = "measurement/";
	protected static String SET_PATH = "building/entry/set/";
	
	protected static int NO_OF_MEASUREMENTS_PER_MIN = 4;
	
	protected static URI getBaseURI() {
		return UriBuilder.fromUri(SERVER_ADDRESS).build();
	}
	protected WebResource resource;

	public AbstractAdapter() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		resource = client.resource(getBaseURI());
	}
}
