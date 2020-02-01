package task;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class ToDo extends Task {
	private String signature = "todo";
	private String separator = "";

	public ToDo(String commandText) {
		super(commandText);
	}

	public ToDo(JSONObject data) throws Exception {
		super(data);
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
		sb.append("  [T]")
		  .append(super.getStatusIcon())
		  .append(super.description);
		return sb.toString();
	}
}