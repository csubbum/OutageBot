package com.bot.outage.service;

import static com.bot.outage.helper.OutageConstant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.outage.domain.Outage;
import com.bot.outage.domain.OutageEvent;
import com.bot.outage.helper.OutageConstant;
import com.bot.outage.helper.OutageException;
import com.bot.outage.repository.OutageRepository;

@Service
public class OutageServiceImpl implements OutageService {

	private final OutageRepository outageRepository;

	public OutageServiceImpl(@Autowired OutageRepository outageRepository) {
		this.outageRepository = outageRepository;
	}

	@Override
	public int save(Outage outage) {
		this.outageRepository.save(outage);
		return outage.getOutageNumber();
	}

	@Override
	public Outage getOutage(int outageNumber) {
		Outage outage = this.outageRepository.getOutage(outageNumber);
		outageNotFound(outage);
		return outage;
	}
	
	@Override
	public void saveStatus(int outageNumber, OutageEvent event) {
		Outage outage = this.getOutage(outageNumber);
		if (outageUpdateAllowed(outage)) {
			outage.addEvent(event);
			save(outage);
		}
	}

	@Override
	public void saveAndStop(int outageNumber, OutageEvent outageEvent) {
		Outage outage = this.getOutage(outageNumber);
		if (outageUpdateAllowed(outage)) {
			outage.addEvent(outageEvent);
			outage.addEvent(new OutageEvent(SYSTEM, END_OF_OUTAGE));
			save(outage);
		}

	}
	
	/*------------------------------ Private methods --------------------------- */

	private void outageNotFound(Outage outage) {
		if (outage == null) {
			throw new OutageException(OutageConstant.INCIDENT_NOT_FOUND);
		}
	}

	private boolean outageUpdateAllowed(Outage outage) {
		if (outage.isEventCompleted()) {
			throw new OutageException(OutageConstant.INCIDENT_EXISTS);
		}
		return true;
	}

	

}
