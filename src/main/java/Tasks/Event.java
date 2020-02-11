package com.duke.task;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import com.duke.dukeException.DukeParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * Consist of event description and period of time in which the event happens.
 */
public class Event extends Task {
	private LocalDate date;
	private String signature = "event";
	public static final String separator = "/at";

	public Event(String commandText) throws DukeParseException {
		super(commandText);
	}

	public Event(JSONObject data) throws DukeParseException {
		super(data);
	}

	@Override
	public String getSignature() {
		return this.signature;
	}

	@Override
	public String getSeparator() {
		return Event.separator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  [E]")
		  .append(super.getStatusIcon())
		  .append(super.description)
		  .append(" (at")
		  .append(super.getRemainingTokens())
		  .append(")");
		return sb.toString();
	}
}