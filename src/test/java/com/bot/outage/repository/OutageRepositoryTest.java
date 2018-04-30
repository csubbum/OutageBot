package com.bot.outage.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bot.outage.domain.Outage;
import com.bot.outage.domain.OutageEvent;

@RunWith(SpringJUnit4ClassRunner.class)
public class OutageRepositoryTest {

	
	private OutageRepository outageRepository;

	public static final Outage TEST_OUTAGE = new Outage("Report Service", "Test Incident Created", "Test Developer",
			"2018-05-05", "06:00:00");
	public static final int INCIDENT_NUMBER = 1012;
	public static final int FAIL_INCIDENT_NUMBER = 1013;
	public static final OutageEvent TEST_EVENT = new OutageEvent("Test dev 2", "Incident status updated");
	
	@Before
	public void setup() {
		outageRepository = new OutageRepositoryImpl(new OutageNumberGenerator() {
			@Override
			public int getNextOutageNumber() {
				return INCIDENT_NUMBER;
			}
		});
		outageRepository.save(TEST_OUTAGE);
	}

	@Test
	public void runTestGetOutage() {
		assertEquals(outageRepository.getOutage(INCIDENT_NUMBER),TEST_OUTAGE);
	}
	
}
