package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;

@Service("roomAdapter")
public class RoomAdapterImpl extends AbstractAdapter implements RoomAdapter {

	@Override
	public MeasurementDto getCurrentRoomLight(String roomId) {
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		params.add("order_by", "-timestamp");
		params.add("limit", "1");
		params.add("uuid", String.format("%s-%s", roomId, "light"));
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

		return result.get(0);
	}


}
