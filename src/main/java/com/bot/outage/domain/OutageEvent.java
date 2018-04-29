package com.bot.outage.domain;

import java.time.LocalDateTime;
import static com.bot.outage.helper.OutageConstant.*;

public class OutageEvent {

	private final LocalDateTime eventTime;
	private final String message;
	private final LocalDateTime reportedTime;
	private final String reportedBy;

	public OutageEvent(String reportedBy, String message) {
		this(reportedBy, message, CURRENT_DATE, CURRENT_TIME);
	}

	public String getMessage() {
		return message;
	}

	public OutageEvent(String reportedBy, String message, String date, String time) {
		this.reportedBy = reportedBy;
		this.message = message;

		if (date == null || date.equals(CURRENT_DATE)) {
			this.eventTime = LocalDateTime.now();
		} else {
			this.eventTime = LocalDateTime.parse(date + "T" + time);
		}
		this.reportedTime = LocalDateTime.now();

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(RECEIVED_MESSAGE);
		sb.append(message);
		sb.append(REPORTED_BY);
		sb.append(reportedBy);
		sb.append(EVENT_TIME);
		sb.append(eventTime);
		sb.append(REPORTED_AT);
		sb.append(reportedTime);
		return sb.toString();
	}
}
