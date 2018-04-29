package com.bot.outage;

import static com.bot.outage.helper.OutageConstant.INCIDENT_WAS_CREATED_SUCCESSFULLY_AND_YOUR_INCIDENT_NUMBER_IS;
import static com.bot.outage.helper.OutageConstant.SUCCESSFULLY_RECORDED;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bot.outage.domain.Outage;
import com.bot.outage.domain.OutageEvent;
import com.bot.outage.service.OutageService;

@RunWith(SpringJUnit4ClassRunner.class)
public class OutageCommandsTest {

	@MockBean
	private OutageService outageService;

	public static final Outage TEST_OUTAGE = new Outage("Report Service", "Test Incident Created", "Test Developer",
			"2018-05-05", "06:00:00");
	public static final int INCIDENT_NUMBER = 1012;
	public static final OutageEvent TEST_EVENT = new OutageEvent("Test dev 2", "Incident status updated");
	OutageCommands outageCommands;
	
	@Before
	public void setup() {
		Mockito.when(outageService.save(Mockito.any(Outage.class))).thenReturn(INCIDENT_NUMBER);
		Mockito.when(outageService.getOutage(INCIDENT_NUMBER)).thenReturn(TEST_OUTAGE);
		outageCommands = new OutageCommands(outageService);
	}

	@Test
	public void runTestStart() {
		assertEquals(outageCommands.start("Report Service", "Test Incident Created", "Test Developer",
				"2018-05-05", "06:00:00"),
				INCIDENT_WAS_CREATED_SUCCESSFULLY_AND_YOUR_INCIDENT_NUMBER_IS + 1012);
	}

	@Test
	public void runTestStatus() {
		assertEquals(outageCommands.status(INCIDENT_NUMBER, "Test Incident Created", "Test Developer",
				"2018-05-05", "06:00:00"), SUCCESSFULLY_RECORDED);
	}

	@Test
	public void runTestSearch() {
		assertEquals(outageCommands.search(INCIDENT_NUMBER), TEST_OUTAGE);
	}

	@Test
	public void runTestStop() {
		assertEquals(outageCommands.stop(INCIDENT_NUMBER, "Test Incident Created", "Test Developer",
				"2018-05-05", "06:00:00"), TEST_OUTAGE);
	}

}