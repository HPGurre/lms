package dk.itu.gsd.lms.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.model.Device;
import dk.itu.gsd.lms.model.Floor;
import dk.itu.gsd.lms.model.ScheduleRuleObject;
import dk.itu.gsd.lms.model.SecurityRuleObject;
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
		for (AbstractRoom room : roomDao.findAll()) {
			Float energy = roomService.getEnergyUsageByDay(room);
			System.out.println("Energy usage by day: " + energy + " kWh");
			energy = roomService.getEnergyUsageByWeek(room);
			System.out.println("Energy usage by week: " + energy + " kWh");
			energy = roomService.getEnergyUsageByMonth(room);
			System.out.println("Energy usage by month: " + energy + " kWh");
			room.setEnergyUsageLastDay(energy);
			room.setEnergyUsageLastWeek(roomService.getEnergyUsageByWeek(room));
			room.setEnergyUsageLastMonth(roomService.getEnergyUsageByMonth(room));
			roomDao.save(room);

			// test getLampMinPower function
			//System.out.println("Lamp min power = " + roomService.getLampMinPower(room));
			//TODO Do we need this.
			// change state of lamps according to the policy model
			//roomService.setLightsAccordingToPolicy(room);
		}

		for (Floor floor : floorDao.findAll()) {
			floor.setEnergyUsageLastDay(floorService.getEnergyUsageByDay(floor.getId()));
			floor.setEnergyUsageLastWeek(floorService.getEnergyUsageByWeek(floor.getId()));
			floor.setEnergyUsageLastMonth(floorService.getEnergyUsageByMonth(floor.getId()));
			floorDao.save(floor);
		}

		for (Building building : buildingDao.findAll()) {
			building.setEnergyUsageLastDay(buildingService.getEnergyUsageByDay(building.getId()));
			building.setEnergyUsageLastWeek(buildingService.getEnergyUsageByWeek(building.getId()));
			building.setEnergyUsageLastMonth(buildingService.getEnergyUsageByMonth(building.getId()));
			buildingDao.save(building);
		}
	}

	@Override
	public void initiateLightTurnOff() {
		logger.debug("Turning off lights in inactive rooms");
		// Go through each room in the building
		for (AbstractRoom room : roomService.findAllRooms()) {
			
			//If lighting is not allowed we turn of the light and continue to next room
			SecurityRuleObject securityPolicy = ruleService.getRoomSecurityPolicy(room);
			if (!securityPolicy.getIsLightAllowed()) {
				for (Device theDevice : room.getDevices()) {
					if (theDevice.getForeignDeviceId().contains("light")) {
						deviceService.turnOffLight(theDevice.getForeignDeviceId());
					}
				}
				continue;
			}
			
			// We want to know if there has been any room activity
			boolean roomActivity = false;
			// hence we look at each device and see if there has been any
			// activity in the last x minutes, where x is taken from the
			// schedule
			for (Device device : room.getDevices()) {
				boolean hasActivityOccured = false;
				ScheduleRuleObject policy = ruleService.getRoomSchedulePolicy(room);
				int minutesBeforeLightShouldTurnOff = policy.getTimeoutLimit();

				// Lights have different output (gain, state)we need to look at.
				if (device.getForeignDeviceId().contains("light")) {
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "gain", minutesBeforeLightShouldTurnOff);
					// hasActivityOccured can be overridden, so store the result now
					if (hasActivityOccured) {
						roomActivity = true;
					}
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "state", minutesBeforeLightShouldTurnOff);
				}
				// Blinds have only gain state to look at.
				if (device.getForeignDeviceId().contains("blind")) {
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "state", minutesBeforeLightShouldTurnOff);

				}
				if (hasActivityOccured) {
					roomActivity = true;
					break;
				}
			}

			// if there has not been any room Activity then we shut off the
			// lights.
			if (!roomActivity) {
				System.out.println("Turning of all lights in room [" + room.getForeignRoomID() + "]");
				for (Device theDevice : room.getDevices()) {
					if (theDevice.getForeignDeviceId().contains("light")) {
						deviceService.turnOffLight(theDevice.getForeignDeviceId());
					}
				}
			}
		}
	}

	@Override
	public void initiateLightAdjustment() {
		logger.debug("Adjusting light level in each room");
		roomService.adjustLight();
		
	}
}
