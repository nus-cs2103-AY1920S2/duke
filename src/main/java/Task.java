package task;
import java.util.List;
import java.util.ArrayList;	
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public abstract class Task {
	protected String commandText;
	protected String description;
	boolean isDone = false;
	protected List<String> remainingTokens = new ArrayList<>();

	public Task(String commandText) {
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
			builder.append(tokens[start]).append(" ");
		}
		if (!okay) {
			this.description = builder.toString();
		}

		remainingTokens = new ArrayList<String>();
		for (start = start + 1; start < tokens.length; start++) {
			remainingTokens.add(tokens[start]);
		}
	}	

	public Task(JSONObject data) throws Exception {
		this.description = (String) data.get("description");
		this.commandText = (String) data.get("commandText");
		this.isDone = ((String) data.get("isDone")).equals("true") ? true : false;
		JSONArray tokensJson = (JSONArray) data.get("tokens");
		remainingTokens = new ArrayList<String>();

		for (int i = 0; i < tokensJson.size(); i++) {
			JSONObject record = (JSONObject) tokensJson.get(i);
			remainingTokens.add((String) record.get("value"));
		}
	}

	public static TaskType getType(String commandText) {
		String[] tokens = commandText.split(" ");

		if (tokens.length >= 2 && tokens[0].equals("todo")) {
			return TaskType.TODO;
		}

		if (tokens.length > 2 && tokens[0].equals("deadline")) {
			return TaskType.DEADLINE;
		}

		if (tokens.length > 2 && tokens[0].equals("event")) {
			return TaskType.EVENT;
		}

		return TaskType.UNDEFINED;
	}

	public String getStatusIcon() {
		return (isDone ? "[\u2713]" : "[\u2718] "); //return tick or X symbols
	}

	public void markAsDone() {
		this.isDone = true;
	}

	public abstract String getSignature();

	public abstract String getSeparator();

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