package duke.ui;

import duke.Duke;
import duke.tasks.Task;
import duke.exceptions.DukeException;
import duke.util.PrintUtil;

import java.util.Scanner;

/**
 * Encapsulates the (command line) user interface provided to the user.
 * The `Cli` class provides utility methods to show various message types.
 * Messages will be displayed in standard output.
 *
 * <p>Header and footer lines are printed by Cli#startMessage() and Cli#endMessage(), respectively.
 */
public class Cli implements Ui {
    Scanner sc;
    //TODO: create and store a Message object instead of using a static buffer
    
    /**
     * Construct a new `Cli` instance.
     * This constructor creates a new `Scanner` reading from standard input,
     * so race conditions may be possible if other `Scanner` objects also read from standard input.
     */
    public Cli() {
        sc = new Scanner(System.in);
    }
    
    /**
     * Denotes the start of a new message to be printed.
     */
    public void startMessage() {
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Denotes the end of a new message to be printed.
     * @return Completed message
     */
    public String endMessage() {
        PrintUtil.printHeaderLine();
        String message = PrintUtil.flushBuffer();
        System.out.print(message);
        return message;
    }
    
    /**
     * Reads a single-line command string from the user.
     * @return command string
     */
    public String readCommandString() {
        return sc.nextLine();
    }
    
    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        PrintUtil.printHeaderLine();
    }
    
    /**
     * Displays a numbered task.
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
     * Displays a message that the task list could not be found.
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
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        PrintUtil.indentedPrintln("Hello from\n" + logo);
    }
    
    /**
     * Displays the goodbye message.
     * Header lines are NOT printed.
     */
    public void showBye() {
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
     * Displays an error message when a command is not recognized.
     * @param command Command string
     */
    public void showUnknownCommandMessage(String command) {
        PrintUtil.indentedPrintf("Error: Unknown command: %s\n", command);
    }
    
    /**
     * Displays a header message to indicate matching tasks from a query.
     * This method only prints the header message.
     */
    public void showMatchingTasksMessage() {
        PrintUtil.indentedPrintln("Here are the matching tasks in your list:");
    }
    
    /**
     * Displays a header message when tasks are sorted.
     * This method only prints the header message.
     */
    public void showSortMessage() {
        PrintUtil.indentedPrintln("Sorted tasks by date. Here are your tasks:");
    }
    
    /**
     * Displays a help command for a given message name.
     * @param commandName Command name
     */
    public void showHelpMessage(String commandName) {
        switch (commandName) {
        case "bye":
            PrintUtil.indentedPrintln("Usage: bye");
            PrintUtil.indentedPrintln("Saves task list to file and exits.");
            break;
        case "deadline":
            PrintUtil.indentedPrintln("Usage: deadline [task description] /by [yyyy-mm-dd]");
            PrintUtil.indentedPrintln("Adds a new Deadline task into the task list.");
            break;
        case "delete":
            PrintUtil.indentedPrintln("Usage: delete [i]");
            PrintUtil.indentedPrintln("Removes the i-th task from the task list.");
            break;
        case "done":
            PrintUtil.indentedPrintln("Usage: done [i]");
            PrintUtil.indentedPrintln("Marks the i-th task in the task list as done.");
            break;
        case "event":
            PrintUtil.indentedPrintln("Usage: event [task description] /at [yyyy-mm-dd]");
            PrintUtil.indentedPrintln("Adds a new Event task into the task list.");
            break;
        case "find":
            PrintUtil.indentedPrintln("Usage: find [task description]");
            PrintUtil.indentedPrintln("Finds and displays tasks whose description matches the query string.");
            break;
        case "list":
            PrintUtil.indentedPrintln("Usage: list");
            PrintUtil.indentedPrintln("Displays the tasks in the task list.");
            break;
        case "sort":
            PrintUtil.indentedPrintln("Usage: sort");
            PrintUtil.indentedPrintln("Sorts all tasks in chronological order, then lists the tasks.");
            break;
        case "todo":
            PrintUtil.indentedPrintln("Usage: todo [task description]");
            PrintUtil.indentedPrintln("Adds a new ToDo task into the task list.");
            break;
        case "help":
            PrintUtil.indentedPrintln("Usage: help [command]");
            PrintUtil.indentedPrintln("Displays help for the specified command.");
            break;
        default:
            PrintUtil.indentedPrintf("Unknown command \"%s\"\n", commandName);
        }
    }
    
    /**
     * Closes and cleans up resources held by the UI.
     * This method does nothing for the command-line interface.
     */
    public void close() {
    }
}

