import java.util.Scanner;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.function.*;
import java.net.URL;
import java.net.MalformedURLException;
import duke.task.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import duke.dukeException.DukeParseException;
import duke.Interpreter;
import duke.Storage;
import duke.TaskList;	
import duke.Command;
import duke.CommandType;

public class Duke {
	static final String separation = "_________________________________________________";
	static final String greetingMessage = "Salue! Je suis Duke. \nWhat can I do for you?";
	static final String goodByeMessage = "Au revoir!";
	static final String pathToData = "data/storage.txt";
	private Storage storage;
	private TaskList taskList;

	public Duke(String pathToData) {
		Storage storage = new Storage();
		TaskList taskList = new TaskList(storage.getData(pathToData));
	}

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
				System.out.println(index);
			} catch (NumberFormatException e) {
				return Optional.empty();
			}
		}
		System.out.println(result.size());
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

	public void run() {
		Interpreter.printGreeting();
		Interpreter.printMessage(greetingMessage);
		
		Interpreter.printUsage();
		Scanner cin = new Scanner(System.in);

		while (true) {
			String commandText = cin.nextLine();
			Command command = new Command(commandText);
			CommandType type = command.getType();

			switch (type) {
				case BYE: 
					Interpreter.printMessage(goodByeMessage);
					storage.saveData(taskList, pathToData);
					return;

				case LIST:
					Interpreter.printList(taskList.getList());
					break;

				case ADD:
					Function<String, Optional<Task>> getTask = (String text) -> {
						try {
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
						} catch (DukeParseException e) {
							System.out.println(e);
							return Optional.empty();
						}
					};
					Optional<Task> currentTask = getTask.apply(commandText);
					if (currentTask.isEmpty()) {
						break;
					}
					taskList.addAction(currentTask.get());
					Interpreter.printAdd(currentTask.get(), taskList.getNum());
					break;

				case DELETE:
					Optional<Integer> index = getDeleteCommand(commandText);
					if (index.isPresent()) {
						int realIndex = index.get() - 1;
						Interpreter.printDelete(taskList.getTask(realIndex), taskList.getNum());
						taskList.deleteAction(realIndex);
					} else {
						System.out.println("Delete command is not valid!");
					}
					break;

				case DONE: 
					Optional<List<Integer>> indexes = getDoneCommand(commandText);
					if (indexes.isPresent()) {
						taskList.markAsDone(indexes.get());
						Interpreter.printDoneList(taskList.getSubset(indexes.get()));
					} else {
						System.out.println("Done command is wrong");
					}
					break;
				default:
					System.out.println("I cannot understand you!");
			}
			
		}
	}

	public static void main(String[] args) {
		new Duke(pathToData).run();
	}
}
