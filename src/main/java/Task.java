package com.duke.task;

import java.util.List;
import java.util.ArrayList;	
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import com.duke.dukeException.DukeParseException;

/**
 * Abstract class Task, provide sharing codes for all task such as
 * event, deadline, todo. 
 */
public abstract class Task {
	protected String commandText;
	public String description;
	boolean isDone = false;
	protected List<String> remainingTokens = new ArrayList<>();

	/**
	 * [Task a task receiving a plain command text]
	 * @param  commandText        [a string typed by users]
	 * @return                    [a new task]
	 * @throws DukeParseException [throws exeption when cannot parse]
	 */
	public Task(String commandText) throws DukeParseException {
		this.commandText = commandText;

		//Parse this task description
		String[] tokens = commandText.split(" ");
		StringBuilder builder = new StringBuilder();
		remainingTokens = new ArrayList<String>();

		int start;
		boolean okay = false;
		for (start = 1; start < tokens.length; start++) { 
			if (tokens[start].equals(this.getSeparator())) {
				this.description = builder.toString();
				okay = true;
				break;
			}
			if (start != 1) {
				builder.append(" ");
			}
			builder.append(tokens[start]);
		}
		if (!okay) {
			this.description = builder.toString();
		}
		remainingTokens = new ArrayList<String>();
		for (start = start + 1; start < tokens.length; start++) {
			remainingTokens.add(tokens[start]);
		}
	}	

	/**
	 * [Task description]
	 * @param  data               [description]
	 * @return                    [description]
	 * @throws DukeParseException [description]
	 */
	public Task(JSONObject data) throws DukeParseException {
		try {
			this.description = (String) data.get("description");
			this.commandText = (String) data.get("commandText");
			this.isDone = ((String) data.get("isDone")).equals("true");
			JSONArray tokensJson = (JSONArray) data.get("tokens");
			remainingTokens = new ArrayList<String>();

			for (int i = 0; i < tokensJson.size(); i++) {
				JSONObject record = (JSONObject) tokensJson.get(i);
				remainingTokens.add((String) record.get("value"));
			}
		} catch (Exception e) {
			throw new DukeParseException("Fail to parse json to a task at task class");
		}
	}

	/**
	 * [getStatusIcon description]
	 * @return [description]
	 */
	public String getStatusIcon() {
		return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
	}
	
	/**
	 * [markAsDone description]
	 */
	public void markAsDone() {
		this.isDone = true;
	}

	/**
	 * [getSignature description]
	 * @return [description]
	 */
	public abstract String getSignature();

	/**
	 * [getSeparator description]
	 * @return [description]
	 */
	public abstract String getSeparator();

	/**
	 * [parseToJson description]
	 * @return [description]
	 */
	public JSONObject parseToJson() {
		JSONObject result = new JSONObject();
		result.put("type", this.getSignature());
		result.put("description", this.description);
		result.put("commandText", this.commandText);
		result.put("isDone", this.isDone ? "true" : "false");
		JSONArray tokens = new JSONArray();
		for (String token: this.remainingTokens) {
			JSONObject element = new JSONObject();
			element.put("value", token);
			tokens.add(element);
		}
		result.put("tokens", tokens);
		return result;
	}
	
	protected List<String> getListRemainingTokens() {
		return this.remainingTokens;
	}

	protected String getRemainingTokens() {
		StringBuilder builder = new StringBuilder();
		for (String token: remainingTokens) {
			builder.append(" ").append(token);
		}
		return builder.toString();
	}

	@Override 
	public String toString() {
		return (this.getStatusIcon() + this.description); 
	}
}