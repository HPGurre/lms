package dk.itu.gsd.lms.services;

import java.util.List;

import dk.itu.gsd.lms.model.LightingBlock;

public interface LightingBlockService {
	
	public LightingBlock createLightingBlock(List<Long> roomId);

}
