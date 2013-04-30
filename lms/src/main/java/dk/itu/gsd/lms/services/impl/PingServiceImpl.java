package dk.itu.gsd.lms.services.impl;

import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.services.PingService;

@Service("pingService")
public class PingServiceImpl implements PingService{

	@Override
	public String ping() {
		return "Pong";
	}
}
