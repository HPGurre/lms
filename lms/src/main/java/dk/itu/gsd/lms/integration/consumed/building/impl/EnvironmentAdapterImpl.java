package dk.itu.gsd.lms.integration.consumed.building.impl;

import org.springframework.stereotype.Component;

import dk.itu.gsd.lms.integration.consumed.building.EnvironmentAdapter;


@Component("environmentAdapter")
public class EnvironmentAdapterImpl extends AbstractAdapter implements EnvironmentAdapter{

	@Override
	public float getSunModelValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCloudModelValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
