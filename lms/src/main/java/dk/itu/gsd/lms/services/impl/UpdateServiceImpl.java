package dk.itu.gsd.lms.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.services.BuildingService;
import dk.itu.gsd.lms.services.DeviceService;
import dk.itu.gsd.lms.services.FloorService;
import dk.itu.gsd.lms.services.LightingBlockService;
import dk.itu.gsd.lms.services.RoomService;
import dk.itu.gsd.lms.services.RuleService;
import dk.itu.gsd.lms.services.UpdateService;

@Service("updateService")
public class UpdateServiceImpl implements UpdateService {
	private static Logger logger = Logger.getLogger(UpdateServiceImpl.class);

	@Autowired
	private RoomService roomService;
	@Autowired
	private FloorService floorService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private FloorDao floorDao;
	@Autowired
	private BuildingDao buildingDao;
	@Autowired
	private LightingBlockService ligtingblockService;

	public void initiateMeasurementUpdate() {
		logger.debug("Updating local measurement data");
		roomService.updateRoomMeasurementdata();
		//floorService.updateFloorMeasurementData();
		//buildingService.updateBuildingMeasurementData();
	}

	@Override
	public void initiateLightTurnOff() {
		logger.debug("Turning off light in inactive rooms");
		roomService.turnOffLight();
	}

	@Override
	public void initiateLightAdjustment() {
		logger.debug("Adjusting light level in each room");
		roomService.adjustLight();
		
	}
}
