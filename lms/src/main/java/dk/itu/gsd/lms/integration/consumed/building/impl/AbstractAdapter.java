package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractAdapter {
	protected static URI getBaseURI() {
		return UriBuilder.fromUri("http://gsd.itu.dk/api/user/buildinginfo/6/")
				.build();
	}

	protected WebResource service;

	public AbstractAdapter() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		service = client.resource(getBaseURI());
	}

}
