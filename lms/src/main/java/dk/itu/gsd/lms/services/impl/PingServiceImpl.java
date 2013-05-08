package dk.itu.gsd.lms.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.services.PingService;

@Service("pingService")
public class PingServiceImpl implements PingService{
	private static Logger logger = Logger.getLogger(PingServiceImpl.class);

	@Override
	public String ping() {
		logger.debug("We have been pinged...");
		return "Pong";
	}
}
