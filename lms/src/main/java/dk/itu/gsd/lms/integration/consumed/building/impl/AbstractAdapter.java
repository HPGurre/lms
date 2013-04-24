package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractAdapter {
	private static String SERVER_ADDRESS = "http://gsd.itu.dk/api/user/";
	public static String LIGHTING_BID = "5";
	
	
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
