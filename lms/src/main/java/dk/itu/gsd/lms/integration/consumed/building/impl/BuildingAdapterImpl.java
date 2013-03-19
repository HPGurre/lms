package dk.itu.gsd.lms.integration.consumed.building.impl;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Component;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import dk.itu.gsd.lms.integration.consumed.building.BuildingAdapter;
import dk.itu.gsd.lms.model.Building;

@Component("buildingAdapter")
public class BuildingAdapterImpl extends AbstractAdapter implements
		BuildingAdapter {

	@Override
	public String getBuildingInfo() {
		//The following URL is an example of what is constructed
		//URL: http://gsd.itu.dk/api/user/buildinginfo
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
	    params.add("format", "json");
	    
		String path = "/buildinginfo/";
		String response = resource.path(path).queryParams(params).get(String.class);
		
		System.out.println(response);
		return response;
			
	}
	public Building getBuildingDescription(Long bid){
		//The following URL is an example of what is constructed
		//http://gsd.itu.dk/api/user/building/entry/description/5
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
	    params.add("format", "json");
		
		String path = String.format("/building/entry/description/%s/", bid);
		String response = resource.path(path).queryParams(params).get(String.class);
		
		System.out.println(response);
		return null;
		
		
		
	}

	public static void main(String[] args) {
		BuildingAdapterImpl bai = new BuildingAdapterImpl();
		bai.getBuildingDescription(5L);
		bai.getBuildingInfo();
	}

	
	
}
