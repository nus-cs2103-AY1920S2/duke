import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A chatbot interface.
 */
public class Duke {
    static String terminateCommand = "bye";
    static ArrayList<String> commands = new ArrayList<String>();

    /**
     * Switching logic for different commands.
     * @param command user command
     */
    private static void commandProcessor(String command) {
        switch (command) {
        case "list":
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i + " " + commands.get(i));
            }
            break;
        default:
            commands.add(command);
            System.out.println("added: " + command);
        }
    }

    /**
     * Long-polling for user commands.
     */
    private static void inputProcessor() {
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();

        while (!userCommand.equals(terminateCommand)) {
            commandProcessor(userCommand);
            userCommand = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Main entry to the bot program.
     * @param args external parameters.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        inputProcessor();
    }
}
