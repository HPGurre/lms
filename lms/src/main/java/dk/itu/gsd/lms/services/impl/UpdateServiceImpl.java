package dk.itu.gsd.lms.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Building;
import dk.itu.gsd.lms.model.Floor;
import dk.itu.gsd.lms.services.BuildingService;
import dk.itu.gsd.lms.services.FloorService;
import dk.itu.gsd.lms.services.LightingBlockService;
import dk.itu.gsd.lms.services.RoomService;
import dk.itu.gsd.lms.services.UpdateService;

@Service("updateService")
public class UpdateServiceImpl implements UpdateService {

	@Autowired
	private RoomService roomService;
	@Autowired
	private FloorService floorService;
	@Autowired
	private BuildingService buildingService;

	@Autowired
	private RoomDao roomDao;
	@Autowired
	private FloorDao floorDao;
	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private LightingBlockService ligtingblockService;

	public void updateEnergyMeasurementsFromBuilding() {

		ligtingblockService.createLightingBlock(new ArrayList<Long>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(1L);
			}
		});

		System.out.println("Updating measurement data for rooms");

		for (AbstractRoom room : roomDao.findAll()) {
			Float energy = roomService.getEnergyUsageByDay(room);
			System.out.println("Energy usage by day: " + energy + " kWh");
			energy = roomService.getEnergyUsageByWeek(room);
			System.out.println("Energy usage by week: " + energy + " kWh");
			energy = roomService.getEnergyUsageByMonth(room);
			System.out.println("Energy usage by month: " + energy + " kWh");
			room.setEnergyUsageLastDay(energy);
			room.setEnergyUsageLastWeek(roomService.getEnergyUsageByWeek(room
					));
			room.setEnergyUsageLastMonth(roomService.getEnergyUsageByMonth(room
					));
			roomDao.save(room);
			
			//test getLampMinPower function
			System.out.println("Lamp min power = " + roomService.getLampMinPower(room));
			
			//change state of lamps according to the policy model
			roomService.setLightsAccordingToPolicy(room);
		}

		System.out.println("Updating measurement data for floors");
		for (Floor floor : floorDao.findAll()) {
			floor.setEnergyUsageLastDay(floorService.getEnergyUsageByDay(floor
					.getId()));
			floor.setEnergyUsageLastWeek(floorService
					.getEnergyUsageByWeek(floor.getId()));
			floor.setEnergyUsageLastMonth(floorService
					.getEnergyUsageByMonth(floor.getId()));
			floorDao.save(floor);
		}

		System.out.println("Updating measurement data for Building");
		for (Building building : buildingDao.findAll()) {
			building.setEnergyUsageLastDay(buildingService
					.getEnergyUsageByDay(building.getId()));
			building.setEnergyUsageLastWeek(buildingService
					.getEnergyUsageByWeek(building.getId()));
			building.setEnergyUsageLastMonth(buildingService
					.getEnergyUsageByMonth(building.getId()));
			buildingDao.save(building);
		}

	}
}
