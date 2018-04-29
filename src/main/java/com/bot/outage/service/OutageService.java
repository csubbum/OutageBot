package com.bot.outage.service;

import com.bot.outage.domain.Outage;
import com.bot.outage.domain.OutageEvent;

public interface OutageService {

	int save(Outage outage);

	void saveStatus(int outageNumber, OutageEvent event);

	Outage getOutage(int outageNumber);

	void saveAndStop(int outageNumber, OutageEvent outageEvent);

}
