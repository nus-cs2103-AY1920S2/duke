package com.duke.task;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.duke.dukeException.DukeParseException;

/**
 * This is TODO task.
 */
public class ToDo extends Task {
	private String signature = "todo";
	private String separator = "";

	/**
	 * [ToDo description]
	 * @param  commandText        [description]
	 * @return                    [description]
	 * @throws DukeParseException [description]
	 */
	
	public ToDo(String commandText) throws DukeParseException {
		super(commandText);
	}

	/**
	 * [ToDo description]
	 * @param  data               [description]
	 * @return                    [description]
	 * @throws DukeParseException [description]
	 */
	public ToDo(JSONObject data) throws DukeParseException {
		super(data);
	}

	/**
	 * [getSignature description]
	 * @return [description]
	 */
	@Override
	public String getSignature() {
		return this.signature;
	}

	/**
	 * [getSeparator description]
	 * @return [description]
	 */
	@Override
	public String getSeparator() {
		return this.separator;
	}

	/**
	 * [toString description]
	 * @return [description]
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  [T]")
		  .append(super.getStatusIcon())
		  .append(super.description);
		return sb.toString();
	}
}