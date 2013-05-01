package dk.itu.gsd.lms.services;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.itu.gsd.lms.model.LightingBlock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class LightingblockServiceTest {

	@Autowired
	private LightingBlockService lightingblockService;

	@Test
	public void test() {
		LightingBlock lb = lightingblockService.createLightingBlock(new ArrayList<Long>() {
			{
				add(1L);
			}
		});
	
	System.out.println(lb.toString());
	
	}
}