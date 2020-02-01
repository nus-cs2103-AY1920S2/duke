import java.util.Scanner;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.function.*;
import java.net.URL;
import java.net.MalformedURLException;
import task.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Duke {
	static final String separation = "_________________________________________________";
	static final String greetingMessage = "Salue! Je suis Duke. \nWhat can I do for you?";
	static final String goodByeMessage = "Au revoir!";
	static final String pathToData = "data/storage.txt";
	

	static Optional<List<Integer>> getDoneCommand(String command) {
		String[] tokens = command.split(" ");
		if (tokens.length < 1) {
			return Optional.empty();
		}
		if (!tokens[0].equals("done")) {
			return Optional.empty();
		}
		List result = new ArrayList<Integer>();
		for (int i = 1; i < tokens.length; i++) {
			try {
				int index = Integer.parseInt(tokens[i]);
				result.add(index - 1);
			} catch (NumberFormatException e) {
				return Optional.empty();
			}
		}
		return Optional.of(result);
	}

	static Optional<Integer> getDeleteCommand(String command) {
		String[] tokens = command.split(" ");
		if (tokens.length != 2) {
			return Optional.empty();
		}
		if (!tokens[0].equals("delete")) {
			return Optional.empty();
		}
		try {
			int index = Integer.parseInt(tokens[1]);
			return Optional.of(index);
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}


	public static void saveData(Storage storage) {
		JSONObject data = storage.encodeContainers();
		String str = data.toString();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(pathToData, false));
		    writer.append(str);
		    writer.close();
		} catch (IOException e) {
			System.out.println("Cannot save data");
		}
	}


	public static List<Task> getData() {
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

	public static void main(String[] args) {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);

		Interpreter.printMessage(greetingMessage);
		Storage storage = new Storage<Task>(getData());
		Scanner cin = new Scanner(System.in);

		while (true) {
			String commandText = cin.nextLine();
			Command command = new Command(commandText);
			CommandType type = command.getType();

			switch (type) {
				case BYE: 
					Interpreter.printMessage(goodByeMessage);
					saveData(storage);
					return;

				case LIST:
					Interpreter.printList(storage.getList());
					break;

				case ADD:
					Function<String, Optional<Task>> getTask = (String text) -> {
						TaskType taskType = Task.getType(text);
						switch (taskType) {
							case TODO:
								return Optional.of(new ToDo(text));
							case EVENT:
								return Optional.of(new Event(text));
							case DEADLINE:
								return Optional.of(new Deadline(text));
							default:
								System.out.println("Adding command is wrong!");
								return Optional.empty();
						}
					};

					Optional<Task> currentTask = getTask.apply(commandText);
					if (currentTask.isEmpty()) {
						break;
					}
					storage.addAction(currentTask.get());
					Interpreter.printAdd(currentTask.get(), storage.getNum());
					break;

				case DELETE:
					Optional<Integer> index = getDeleteCommand(commandText);
					if (index.isPresent()) {
						int realIndex = index.get() - 1;
						Interpreter.printDelete(storage.getTask(realIndex), storage.getNum());
						storage.deleteAction(realIndex);
					} else {
						System.out.println("Delete command is not valid!");
					}
					break;

				case DONE: 
					Optional<List<Integer>> indexes = getDoneCommand(commandText);
					if (indexes.isPresent()) {
						storage.markAsDone(indexes.get());
					}
					break;
				default:
					System.out.println("I cannot understand you!");
			}
			
		}
	}
}
