package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.integration.consumed.building.FloorAdapter;
import dk.itu.gsd.lms.services.FloorService;

@Service("floorService")
public class FloorServiceImpl implements FloorService {
	@Autowired
	FloorAdapter floorAdapter;

}
