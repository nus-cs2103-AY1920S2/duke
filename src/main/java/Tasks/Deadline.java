package com.duke.task;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.List;
import com.duke.dukeException.DukeParseException;

public class Deadline extends Task {
	private LocalDate date;
	private String signature = "deadline";
	public static final String separator = "/by";

	private void parseTime() throws DukeParseException {
		try {
			List<String> remainingTokens = super.getListRemainingTokens();
			if (remainingTokens.size() == 0) {
				throw new DukeParseException("Events need date and time!");
			}
			if (remainingTokens.size() > 1) {
				throw new DukeParseException("Events requires only one string");
			}
			assert(remainingTokens.size() == 1);
			this.date = LocalDate.parse(remainingTokens.get(0));
		} catch (Exception e) {
			throw new DukeParseException("Events time format error");
		}
	}

	public Deadline(String commandText) throws DukeParseException {
		super(commandText);
		parseTime();
	}

	public Deadline(JSONObject data) throws DukeParseException {
		super(data);
		parseTime();
	}

	@Override
	public String getSignature() {
		return this.signature;
	}

	@Override
	public String getSeparator() {
		return this.separator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  [D]")
		  .append(super.getStatusIcon())
		  .append(" ")
		  .append(super.description)
		  .append(" (by ")
		  .append(this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)))
		  .append(")");
		return sb.toString();
	}
}