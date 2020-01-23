import java.util.List;
import task.*;

public class Interpreter {
	static private final String separation = "_________________________________________________";

	static public void printMessage(String message) {
		System.out.println(separation);
		System.out.println(message);
		System.out.println(separation);
	}

	static public void printList(List<Object> list) {
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

	static public void printActionList(List<Task> list) {
		System.out.println(separation);
		System.out.println("Nice! I've marked this task as done:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("  " + list.get(i));
		}
		System.out.println(separation);
	}
}