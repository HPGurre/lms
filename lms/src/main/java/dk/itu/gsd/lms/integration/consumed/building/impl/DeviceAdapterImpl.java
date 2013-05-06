package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import dk.itu.gsd.lms.integration.consumed.building.DeviceAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;

@Component("deviceAdapter")
public class DeviceAdapterImpl extends AbstractAdapter implements DeviceAdapter {

	@Override
	public boolean toggleLight(String deviceId, float gain) {
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		
		String path = SET_PATH+LIGHTING_BID	+"/"+deviceId+"-gain/"+gain;	
		String result = resource.path(path).queryParams(params).get(String.class);
		return result.contains("true")? true:false;
	}

	// return measurements by number of measurements
	public List<MeasurementDto> getLatestDeviceMeasurements(String deviceId, String type, int minutes) {

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		params.add("order_by", "-timestamp");
		params.add("limit", String.format("%s", minutes + NO_OF_MEASUREMENTS_PER_MIN));
		params.add("uuid", String.format("%s-%s", deviceId, type));
		params.add("bid", LIGHTING_BID);

		String path = "measurement/";
		String response = resource.path(path).queryParams(params).get(String.class);

		List<MeasurementDto> result = new ArrayList<MeasurementDto>();
		Gson gson = new Gson();

		JsonParser parser = new JsonParser();
		JsonObject e = (JsonObject) parser.parse(response);

		JsonArray array = (JsonArray) e.get("objects");
		for (JsonElement jsonElement : array) {
			result.add(gson.fromJson(jsonElement, MeasurementDto.class));
		}

		return result;
	}
}
