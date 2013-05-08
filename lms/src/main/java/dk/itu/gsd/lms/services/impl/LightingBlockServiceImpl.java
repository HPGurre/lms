package dk.itu.gsd.lms.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.LightingblockDao;
import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.model.Room;
import dk.itu.gsd.lms.model.LightingBlock;
import dk.itu.gsd.lms.services.LightingBlockService;

@Service("lightingBlockService")
public class LightingBlockServiceImpl implements LightingBlockService {
	
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private LightingblockDao lightingblockDao;

	@Override
	public LightingBlock createLightingBlock(List<Long> roomIds) {
		LightingBlock lightingBlock = new LightingBlock();
		lightingBlock = lightingblockDao.save(lightingBlock);
		
		
		
		List<Room> rooms = new ArrayList<Room>();
		lightingBlock.setRooms(rooms);
		for (Long id : roomIds) {
			lightingBlock.getRooms().add(roomDao.find(id));
		}
		
		
		return lightingblockDao.save(lightingBlock);
	}
}
