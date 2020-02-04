import java.util.Scanner;

public class Ui {

    Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    static String logo = " ____        _        \n\t"
            + "|  _ \\ _   _| | _____ \n\t"
            + "| | | | | | | |/ / _ \\\n\t"
            + "| |_| | |_| |   <  __/\n\t"
            + "|____/ \\__,_|_|\\_\\___|\n\t";

    /**
     * Greetings to be printed.
     */
    public String greetings() {
        String output = "Hello from\n\t" + logo + "\n";
        return output + "What can I do for you?\n";
    }

    public String getInput() {
        return in.nextLine();
    }

    /**
     * Prints error when there is no description.
     */
    public String descriptionError() {
        return "Command has the incorrect format\n";
    }

    /**
     * Printing goodbye messages.
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Print error in the command.
     */
    public String commandError() {
        return "Please enter a valid command: todo, deadline, event, list, bye, find, delete.\n";
    }

    /**
     * Print error message when task is invalid.
     */
    public String invalidTaskError() {
        return "Task doesn't exist. Please choose another.\n";
    }

    public String horizontalLine() {
        return "____________________________________________________________\n";
    }

}
