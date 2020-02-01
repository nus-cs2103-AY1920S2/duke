package duke;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.task.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Storage {
	private String pathToData;

	public Storage(String pathToData) {
		this.pathToData = pathToData;
	}

	public JSONObject encodeContainers(List<Task> containers) {
		JSONObject result = new JSONObject();
		JSONArray parsedContainers = new JSONArray();
		for (Task current: containers) {
			parsedContainers.add(current.parseToJson());
		}
		result.put("containers", parsedContainers);
		return result;
	}

	public List<Task> getData() {
		try {
			File file = new File(pathToData);
			Scanner scan = new Scanner(file);
			StringBuilder sb = new StringBuilder();
			while (scan.hasNextLine()) {
				sb.append(scan.nextLine());
			}
			scan.close();
			JSONParser parser = new JSONParser();
			//System.out.println(sb.toString());
			JSONObject result = (JSONObject) parser.parse(sb.toString());
			JSONArray array = (JSONArray) result.get("containers");

			List containers = new ArrayList<Task>();
			for (int i = 0; i < array.size(); i++) {
				JSONObject record = (JSONObject) array.get(i);
				String type = (String) record.get("type");
				try {
					switch (type) {
					case "todo":
						containers.add(new ToDo(record));
						break;
					case "deadline":
						containers.add(new Deadline(record));
						break;
					case "event":
						containers.add(new Event(record));
						break;
					}
				} catch (Exception e) {
					System.out.println("Cannot parse the " + i + "record");
				}
			}
			return containers;
		} catch (IOException e) {
			System.out.println("Cannot read file");
			return new ArrayList<Task>();
		} catch (ParseException e) {
			System.out.println("Cannot parse json string");
			return new ArrayList<Task>();
		}
	}

	public void saveData(TaskList storage) {
		JSONObject data = this.encodeContainers(storage.getList());
		String str = data.toString();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(pathToData, false));
		    writer.append(str);
		    writer.close();
		} catch (IOException e) {
			System.out.println("Cannot save data");
		}
	}


}