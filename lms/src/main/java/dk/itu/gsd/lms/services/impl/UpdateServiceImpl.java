package dk.itu.gsd.lms.services.impl;

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

	public void updateEnergyMeasurementsFromBuilding() {
		System.out.println("Updating measurement data for rooms");

		for (AbstractRoom room : roomDao.findAll()) {
			Float energy = roomService.getEnergyUsageByDay(room);
			System.out.println("Energy: " + energy + " kWh");
			room.setEnergyUsageLastDay(energy);
			room.setEnergyUsageLastWeek(roomService.getEnergyUsageByWeek(room
					.getId()));
			room.setEnergyUsageLastMonth(roomService.getEnergyUsageByMonth(room
					.getId()));
			roomDao.save(room);
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