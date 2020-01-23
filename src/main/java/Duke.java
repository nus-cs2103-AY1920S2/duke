import java.util.Scanner;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.function.*;
import task.*;

public class Duke {
	static final String separation = "_________________________________________________";
	static final String greetingMessage = "Salue! Je suis Duke. \nWhat can I do for you?";
	static final String goodByeMessage = "Au revoir!";
	

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

	public static void main(String[] args) {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);

		Interpreter.printMessage(greetingMessage);
		Storage storage = new Storage<Task>();

		Scanner cin = new Scanner(System.in);
		while (true) {
			String commandText = cin.nextLine();
			Command command = new Command(commandText);
			CommandType type = command.getType();

			switch (type) {
				case BYE: 
					Interpreter.printMessage(goodByeMessage);
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
