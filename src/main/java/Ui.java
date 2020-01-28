import java.util.Scanner;

/**
 * Encapsulates the (command line) user interface provided to the user.
 * The `Ui` class provides utility methods to show various message types.
 * Messages will be displayed in standard output.
 *
 * Most of the methods do not print any header line, with the exception of `greet()`
 */
public class Ui {
    Scanner sc;
    
    /**
     * Construct a new `Ui` instance.
     * This constructor creates a new `Scanner` reading from standard input,
     * so race conditions may be possible if other `Scanner` objects also read from standard input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    /**
     * Reads a single-line command string from the user.
     * @return command string
     */
    public String readCommandString() {
        return sc.nextLine();
    }
    
    /**
     * Displays a horizontal line
     */
    public void showLine() {
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a numbered task
     * @param index Index of the task
     * @param task Task to be displayed
     */
    public void showNumberedEntry(int index, Task task) {
        PrintUtil.indentedPrintf("%d.%s\n", index, task);
    }
    
    /**
     * Displays the message of the provided `DukeException`.
     * @param e exception of type DukeException
     */
    public void showError(DukeException e) {
        PrintUtil.indentedPrintf("Error: %s\n",e.getMessage());
    }
    
    /**
     * Displays a message that the task list could not be found
     * @param savePath Intended path of the file
     */
    public void showSaveNotFoundMessage(String savePath) {
        PrintUtil.printHeaderLine();
        PrintUtil.indentedPrintln("Error: Task list not found");
        PrintUtil.indentedPrintf("       Duke will create a new task list file at %s\n", savePath);
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a greeting, separated by horizontal header lines.
     */
    public void greet() {
        PrintUtil.printHeaderLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        PrintUtil.indentedPrintln("Hello from\n" + logo);
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays the goodbye message.
     * Header lines are NOT printed.
     */
    public void bye() {
        PrintUtil.indentedPrintln("Goodbye");
    }
    
    /**
     * Displays a message when a task is added.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showAddTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Added task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    /**
     * Displays a message when a task is removed.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showRemoveTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Removed task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    /**
     * Displays a message when a task is marked as completed.
     * @param task Task marked as complete
     */
    public void showDoneTaskMessage(Task task) {
        PrintUtil.indentedPrintf("Marked task as done:\n  %s\n", task);
    }
    
    /**
     * Displays an error message when a command is not recognized
     * @param command Command string
     */
    public void showUnknownCommandMessage(String command) {
        PrintUtil.indentedPrintf("Error: Unknown command: %s\n", command);
    }
    
    public void showMatchingTasksMessage() {
        PrintUtil.indentedPrintln("Here are the matching tasks in your list:");
    }
}
