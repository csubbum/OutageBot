package com.bot.outage.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bot.outage.domain.Outage;

@Repository
public class OutageRepositoryImpl implements OutageRepository {

	private final Map<Integer, Outage> outageMap;
	private final OutageNumberGenerator outageNumberGenerator;
	
	public OutageRepositoryImpl(@Autowired OutageNumberGenerator outageNumberGenerator) {
		this.outageMap = new HashMap<>(10);
		this.outageNumberGenerator = outageNumberGenerator;
	}
	
	@Override
	public Outage getOutage(int outageNumber) {
		if(outageMap.containsKey(outageNumber)) {
			return this.outageMap.get(outageNumber);
		}
		return null;
	}

	@Override
	public void save(Outage outage) {
		if (outage.getOutageNumber() == null) {
			int outageNumber = this.outageNumberGenerator.getNextOutageNumber();
			outage.setOutageNumber(outageNumber);
		}
		this.outageMap.put(outage.getOutageNumber(), outage);
	}
	
}
