package duke;
import java.util.List;
import duke.task.*;
import duke.dukeException.DukeException;

public class Interpreter {
	static private final String separation = "_________________________________________________";
	static private final String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
	
	static public void printGreeting() {
		System.out.println("Hello from\n" + logo);
	}

	static public void printUsage() {
		System.out.println("Usage of DUKE: \n");
		System.out.println("1. todo ... ");
		System.out.println("2. deadline ... /by yyyy-mm-dd");
		System.out.println("3. event ... /at .....");
		System.out.println(separation);
	}

	static public void printMessage(String message) {
		System.out.println(separation);
		System.out.println(message);
		System.out.println(separation);
	}

	static public void printList(List<Task> list) {
		System.out.println(separation);
		System.out.println("List of items: ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". " + list.get(i));
		}
		System.out.println(separation);
	}

	static public void printAdd(Task task, int numberOfCurrentTasks) {
		System.out.println(separation);
		System.out.println("Got it. I've added this task:");
		System.out.println(task);
		System.out.println("Now you have " + numberOfCurrentTasks + " in the list!");
		System.out.println(separation);
	}

	static public void printDelete(Task task, int numberOfCurrentTasks) {
		System.out.println(separation);
		System.out.println("Noted. I've removed this task:");
		System.out.println(task);
		System.out.println("Now you have " + numberOfCurrentTasks + " in the list!");
		System.out.println(separation);
	}

	static public void printDoneList(List<Task> list) {
		System.out.println(separation);
		System.out.println("Nice! I've marked this task as done:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("  " + list.get(i));
		}
		System.out.println(separation);
	}

	static public void printException(DukeException e) {
		System.out.println(separation);
		System.out.println(e);
		System.out.println(separation);
	}

	static public void printFind(List<Task> tasks, List<Integer> indexes) {
		System.out.println(separation);
		System.out.println("Here are the matching tasks in your list:");
		for (Integer idx: indexes) {
			System.out.println((idx + 1) + ". " + tasks.get(idx));
		}
		System.out.println(separation);
	}
}