package duke;
import java.util.ArrayList;
import java.util.List;
import duke.task.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.net.URL;


public class TaskList {
	private List<Task> containers;

	public TaskList() {
		this.containers = new ArrayList<Task> ();
	}

	public TaskList(List<Task> containers) {
		this.containers = containers;
	}

	public void addAction(Task action) {
		containers.add(action);
	}

	public void deleteAction(int position) {
		containers.remove(position);
	}

	public List<Task> getList() {
		return this.containers;
	} 

	public Task getTask(int index) {
		return this.containers.get(index);
	}

	public int getNum() {
		return containers.size();
	}

	public void markAsDone(List<Integer> needy) {
		for (Integer index: needy) {
			this.containers.get(index).markAsDone();
		}
	}

	public List<Task> getSubset(List<Integer> needy) {
		List result = new ArrayList<Task>();
		for (Integer index: needy) {
			result.add(this.containers.get(index));
		}
		return result;
	}
}