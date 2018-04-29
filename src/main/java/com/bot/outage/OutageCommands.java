package com.bot.outage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.bot.outage.domain.Outage;
import com.bot.outage.domain.OutageEvent;
import com.bot.outage.service.OutageService;

import static com.bot.outage.helper.OutageConstant.*;

@ShellComponent
public class OutageCommands {

	private final OutageService outageService;

	public OutageCommands(@Autowired OutageService outageService) {
		this.outageService = outageService;
	}

	/**
	 * Create an incident.
	 * 
	 * @param systemName
	 * @param message
	 * @param reportedBy
	 * @param incidentDate optional
	 * @param incidentTime optional
	 * 
	 * @return Incident number that was created
	 */
	@ShellMethod("Create an incident, this will create a start event")
	public String start(@ShellOption(help = "Outage system name") String systemName,
			@ShellOption(help = "Outage message") String message, @ShellOption(help = "Reported by") String reportedBy,
			@ShellOption(defaultValue = CURRENT_DATE, help = "incidentDate format YYYY-mm-dd ex 2018-01-01") String incidentDate,
			@ShellOption(defaultValue = CURRENT_TIME, help = "incidentTime format HH:MM:SS 06:30:00") String incidentTime) {
		Outage outage = new Outage(systemName, message, reportedBy, incidentDate, incidentTime);
		return INCIDENT_WAS_CREATED_SUCCESSFULLY_AND_YOUR_INCIDENT_NUMBER_IS + outageService.save(outage);
	}

	/**
	 * Add status to an existing incident/outage.
	 * 
	 * @param outageNumber
	 * @param reportedBy
	 * @param message
	 * @param incidentDate
	 * @param incidentTime
	 * @return Successfully recorded or generated error
	 */
	@ShellMethod("Report status an incident")
	public String status(@ShellOption(help = "Outage number") int outageNumber,
			@ShellOption(help = "Reported by") String reportedBy, @ShellOption(help = "Message ") String message,
			@ShellOption(defaultValue = CURRENT_DATE, help = "incidentDate format YYYY-mm-dd ex 2018-01-01") String incidentDate,
			@ShellOption(defaultValue = CURRENT_TIME, help = "incidentTime format HH:MM:SS 06:30:00") String incidentTime) {
		outageService.saveStatus(outageNumber,new OutageEvent(reportedBy, message, incidentDate, incidentTime));
		return SUCCESSFULLY_RECORDED;
	}

	/**
	 * Search outage using the outage number.
	 * 
	 * @param outageNumber
	 * @return Details of the outage with all the status.
	 */
	@ShellMethod("Search an incident")
	public Outage search(@ShellOption(help = "Outage number") int outageNumber) {
		return outageService.getOutage(outageNumber);
	}

	/**
	 * End the incident/outage.
	 * 
	 * @param outageNumber
	 * @param reportedBy
	 * @param message
	 * @param incidentDate
	 * @param incidentTime
	 * @return Outage with all the status information
	 */
	@ShellMethod("End an incident")
	public Outage stop(@ShellOption(help = "Outage number") int outageNumber,
			@ShellOption(help = "Reported by") String reportedBy, @ShellOption(help = "Message ") String message,
			@ShellOption(defaultValue = CURRENT_DATE, help = "incidentDate format YYYY-mm-dd ex 2018-01-01") String incidentDate,
			@ShellOption(defaultValue = CURRENT_TIME, help = "incidentTime format HH:MM:SS 06:30:00") String incidentTime) {
		outageService.saveAndStop(outageNumber, new OutageEvent(reportedBy, message, incidentDate, incidentTime));
		return outageService.getOutage(outageNumber);
	}
}
