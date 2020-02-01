import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import duke.task.*;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import duke.dukeException.DukeParseException;
import duke.Interpreter;
import duke.Storage;
import duke.TaskList;	
import duke.Parser;
import duke.commands.Command;

public class Duke {
	static final String separation = "_________________________________________________";
	static final String greetingMessage = "Salue! Je suis Duke. \nWhat can I do for you?";
	static final String pathToData = "data/storage.txt";
	private Storage storage;
	private TaskList taskList;
	private Parser parser;

	public Duke(String pathToData) {
		storage = new Storage(pathToData);
		taskList = new TaskList(storage.getData());
		parser = new Parser();
	}

	public void run() {
		Interpreter.printGreeting();
		Interpreter.printMessage(greetingMessage);
		Interpreter.printUsage();

		Scanner cin = new Scanner(System.in);

		while (true) {
			String commandText = cin.nextLine();
			try {
				Command current = parser.parse(commandText);
				current.execute(storage, taskList);
				if (current.isExit()) {
					return;
				}
			} catch (DukeParseException e) {
				Interpreter.printException(e);
			}
		}
	}

	public static void main(String[] args) {
		new Duke(pathToData).run();
	}
}
