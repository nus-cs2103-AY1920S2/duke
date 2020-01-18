import java.io.InputStream;
import java.util.Scanner;

public class Duke {
    protected static final String HORIZONTAL_BAR =
            "____________________________________________________________";
    protected static final String NEWLINE = System.lineSeparator();
    protected static final String INDENTATION = "    ";
    protected static Scanner scanner;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.processCommands(System.in);
        duke.goodbye();
    }

    /**
     * Prints given text with indentation: specified in Duke class.
     * @param text used for formatting and printing
     */
    protected void printTextWithIndentation(String text) {
        System.out.println(INDENTATION + text);
    }

    /**
     * Prints greeting message.
     */
    protected void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Hello! I'm Duke");
        printTextWithIndentation("What can I do for you?");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Prints goodbye message.
     */
    protected void goodbye() {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Bye. Hope to see you again soon!");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Process input given by user and execute relevant actions.
     */
    protected void processCommands(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else {
                printTextWithIndentation(HORIZONTAL_BAR);
                printTextWithIndentation(command);
                printTextWithIndentation(HORIZONTAL_BAR);
            }
        }
        scanner.close();
    }
}
