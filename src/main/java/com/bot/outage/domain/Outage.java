package com.bot.outage.domain;

import java.util.ArrayList;
import java.util.List;
import static com.bot.outage.helper.OutageConstant.*;

public class Outage {
	
	private Integer outageNumber;
	private final String systemName;
	private final String message;
	
	
	private final List<OutageEvent> events;
	
	public Outage(String systemName, String message, String reportedBy, String incidentDate, String incidentTime) {
		this.systemName = systemName;
		this.message = message;
		this.events = new ArrayList<>(5);
		this.events.add(new OutageEvent(reportedBy, OUTAGE_STARTED, incidentDate, incidentTime));
		
	}
	
	public Integer getOutageNumber() {
		return outageNumber;
	}

	public void setOutageNumber(Integer outageNumber) {
		this.outageNumber = outageNumber;
	}

	public void addEvent(OutageEvent event) {
		this.events.add(event);
	}
	
	public Boolean isEventCompleted() {
		for(OutageEvent outageEvent : events) {
			if(outageEvent.getMessage().equals(END_OF_OUTAGE)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(SYSTEM_NAME);
		sb.append(systemName);
		sb.append(OUTAGE_NUMBER);
		sb.append(outageNumber);
		sb.append(MESSAGE);
		sb.append(message);
		sb.append(NEW_LINE);
		sb.append(LINE);
		for (OutageEvent event : this.events) {
			sb.append(event.toString() + NEW_LINE);
		}
		return sb.toString();
	}
}
