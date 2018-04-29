package com.bot.outage.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmlunit.input.WhitespaceNormalizedSource;

import com.bot.outage.domain.Outage;
import com.bot.outage.repository.OutageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class OutageServiceTest {

	@MockBean
	private OutageRepository outageRepository;
	
	private static final int OUTAGE_NUMBER = 1001;
	public static final Outage TEST_OUTAGE = new Outage("Report Service", "Test Incident Created", "Test Developer",
			"2018-05-05", "06:00:00");
	
	
	OutageService outageService;

	@Before
	public void setup() {
		outageService = new  OutageServiceImpl(outageRepository);
		TEST_OUTAGE.setOutageNumber(OUTAGE_NUMBER);
		Mockito.when(outageRepository.getOutage(OUTAGE_NUMBER)).thenReturn(TEST_OUTAGE);
	}

	@Test
	public void runTestGetOutage() {
		assertEquals(outageService.getOutage(OUTAGE_NUMBER),TEST_OUTAGE);
	}
	
	@Test
	public void runTestSave() {
		assertEquals(outageService.save(TEST_OUTAGE),OUTAGE_NUMBER);
	}
}
