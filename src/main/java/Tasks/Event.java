package duke.task;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import dukeException.DukeParseException;

public class Event extends Task {
	private LocalDate date;
	private String signature = "event";
	private String sepapartor = "/at";

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
		return this.sepapartor;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  [E]")
		  .append(super.getStatusIcon())
		  .append(super.description)
		  .append(" (by")
		  .append(this.date.toString())
		  .append(")");
		return sb.toString();
	}
}