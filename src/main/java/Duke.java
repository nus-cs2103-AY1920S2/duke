import java.util.Scanner;

public class Duke {
	static final String separation = "_________________________________________________";
	static final String greetingMessage = "Salue! Je suis Duke. \nWhat can I do for you?";
	static final String goodByeMessage = "Au revoir!";

	static void printMessage(String message) {
		System.out.println(separation);
		System.out.println(message);
		System.out.println(separation);
	}

	public static void main(String[] args) {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);

		printMessage(greetingMessage);

		Scanner cin = new Scanner(System.in);
		while (true) {
			String command = cin.nextLine();

			if (command.equals("bye")) {
				printMessage(goodByeMessage);
				break;
			} else {
				printMessage(command);
				continue;
			}
		}
	}
}
