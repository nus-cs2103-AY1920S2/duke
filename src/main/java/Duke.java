import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static void hLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        hLine();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        hLine();
    }

    private static void printWithHLine(String message) {
        hLine();
        System.out.println(message);
        hLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getErrorMessage());
            } finally {
                // do nothing
            }
        }

        printWithHLine("Bye. Hope to see you again soon!");
    }

}