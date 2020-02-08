import java.util.List;

/**
 * Handles the (command line) user interface provided to the user.
 * The Ui class provides utility methods to show various message types.
 * Messages will be displayed in standard output.
 */
public class Ui {

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
    }

    /**
     * Prints the string.
     *
     * @param s The string.
     */
    private void print(String s) {
        System.out.println(s);
    }

    /**
     * Prints a horizontal line.
     */
    private void printLine() {
        print("____________________________________________________");
    }

    /**
     * Displays a greeting, separated by horizontal header lines.
     */
    public void greet() {
        printLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        print("Hello from\n" + logo);
        print("What can I do for you?");
        printLine();
    }

    /**
     * Prints all the tasks stored in the given list.
     *
     * @param tasks The list containing all the tasks.
     * @throws DukeInvalidTaskException If the list of tasks is empty.
     */
    public void displayTasks(List<Task> tasks) throws DukeInvalidTaskException {
        if (tasks.size() == 0) {
            throw new DukeInvalidTaskException("There are no remaining tasks in the list");
        }

        print("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            print(i + "." + tasks.get(i - 1));
        }
        printLine();
    }

    /**
     * Prints the list of all the tasks matching a string keyword.
     *
     * @param tasks The list containing all the tasks.
     * @throws DukeInvalidTaskException If the task is not found.
     */
    public void displayFoundTasks(List<Task> tasks) throws DukeInvalidTaskException {
        if (tasks.size() == 0) {
            throw new DukeInvalidTaskException("There are no matching tasks in your list");
        }

        print("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            print(i + "." + tasks.get(i - 1));
        }
        printLine();
    }

    /**
     * Prints the error message.
     *
     * @param e The error that is being raised.
     */
    public void printError(Exception e) {
        print(e.toString());
        print("Please try again.");
    }

    /**
     * Prints the invalid date format error message.
     */
    public void printInvalidDateFormatError() {
        print("Please enter the date as yyyy-mm-dd followed by the time e.g. 2020-01-01 2359");
        print("Try again.");
    }

    /**
     * Displays the goodbye message.
     * followed by a horizontal line.
     */
    public void printBye() {
        print("Bye. Hope to see you again soon!");
        printLine();
        System.exit(0);
    }
}
