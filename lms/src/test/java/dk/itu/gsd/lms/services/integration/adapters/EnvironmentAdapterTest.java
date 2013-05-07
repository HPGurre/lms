package dk.itu.gsd.lms.services.integration.adapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.itu.gsd.lms.integration.consumed.building.EnvironmentAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class EnvironmentAdapterTest {

	@Autowired
	private EnvironmentAdapter environmentAdapter;

	@Test
	public void testSun() {
		float result = environmentAdapter.getSunModelValue();
		System.out.println("VALUE of SUN:" + result);
	}
	
	@Test
	public void testCloud() {
		float result = environmentAdapter.getCloudModelValue();
		System.out.println("VALUE of CLOUD " + result);
	}

}
