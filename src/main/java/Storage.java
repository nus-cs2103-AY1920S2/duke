import java.util.ArrayList;
import java.util.List;
import task.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.net.URL;


public class Storage<T extends Task> {
	static final String pathToData = "./data/storage.txt";
	private List<T> containers;

	public JSONObject encodeContainers() {
		JSONObject result = new JSONObject();
		JSONArray parsedContainers = new JSONArray();
		for (T current: containers) {
			parsedContainers.add(current.parseToJson());
		}
		result.put("containers", parsedContainers);
		return result;
	}

	public Storage(List<T> containers) {
		this.containers = containers;
	}

	public void addAction(T action) {
		containers.add(action);
	}

	public void deleteAction(int position) {
		containers.remove(position);
	}

	public List<T> getList() {
		return this.containers;
	} 

	public T getTask(int index) {
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

	public List<T> getSubset(List<Integer> needy) {
		List result = new ArrayList<T>();
		for (Integer index: needy) {
			result.add(this.containers.get(index));
		}
		return result;
	}
}