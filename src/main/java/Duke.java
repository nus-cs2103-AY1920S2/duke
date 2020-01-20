import java.util.Scanner;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

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
					Task currentTask = new Task(commandText);
					storage.addAction(currentTask);
					Interpreter.printMessage("added :" + commandText);
					break;

				case DONE: 
					Optional<List<Integer>> indexes = getDoneCommand(commandText);
					if (indexes.isPresent()) {
						storage.markAsDone(indexes.get());
					}
					break;
			}
		}
	}
}
