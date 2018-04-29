package com.bot.outage.repository;

import org.springframework.stereotype.Component;

@Component
public class OutageNumberGeneratorImpl implements OutageNumberGenerator {
	
	int currentOutageNumber = 1000;
	
	@Override
	public synchronized int getNextOutageNumber() {
		return ++currentOutageNumber;
	}
}
