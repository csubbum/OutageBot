package com.bot.outage.repository;

import com.bot.outage.domain.Outage;

public interface OutageRepository {
	Outage getOutage(int uuid);
	void save(Outage outage);
}
