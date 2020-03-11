package duke.ui;

import duke.Duke;
import duke.tasks.Task;
import duke.exceptions.DukeException;

import java.util.Scanner;

/**
 * Encapsulates an abstract user interface provided to the user.
 * The `Ui` interface specifies methods displaying messages to the user.
 * Implementations of the `Ui` interface are not required to buffer the printed message,
 * but the whole message must be printed after Ui#endMessage() is called.
 */
public interface Ui {
    //TODO: create and store a Message object instead of using a static buffer
    
    /**
     * Denotes the start of a new message to be printed.
     */
    public void startMessage();
    
    /**
     * Denotes the end of a new message to be printed.
     * @return Completed message
     */
    public String endMessage();
    
    /**
     * Reads a single-line command string from the user.
     * @return command string
     */
    public String readCommandString();
    
    /**
     * Displays a horizontal line.
     */
    public void showLine();
    
    /**
     * Displays a numbered task.
     * @param index Index of the task
     * @param task Task to be displayed
     */
    public void showNumberedEntry(int index, Task task);
    
    /**
     * Displays the message of the provided `DukeException`.
     * @param e exception of type DukeException
     */
    public void showError(DukeException e);
    
    /**
     * Displays a message that the task list could not be found.
     * @param savePath Intended path of the file
     */
    public void showSaveNotFoundMessage(String savePath);
    
    /**
     * Displays a greeting.
     */
    public void showGreeting();
    
    /**
     * Displays the goodbye message.
     * Header lines are not printed.
     */
    public void showBye();
    
    /**
     * Displays a message when a task is added.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showAddTaskMessage(Task task, int remainingCount);
    
    /**
     * Displays a message when a task is removed.
     * @param task Task added
     * @param remainingCount New number of tasks
     */
    public void showRemoveTaskMessage(Task task, int remainingCount);
    
    /**
     * Displays a message when a task is marked as completed.
     * @param task Task marked as complete
     */
    public void showDoneTaskMessage(Task task);
    
    /**
     * Displays an error message when a command is not recognized.
     * @param command Command string
     */
    public void showUnknownCommandMessage(String command);
    
    /**
     * Displays a header message to indicate matching tasks from a query.
     * This method only prints the header message.
     */
    public void showMatchingTasksMessage();
    
    /**
     * Displays a header message when tasks are sorted.
     * This method only prints the header message.
     */
    public void showSortMessage();
    
    /**
     * Displays a help command for a given message name.
     * @param commandName Command name
     */
    public void showHelpMessage(String commandName);
    
    /**
     * Closes and cleans up resources held by the UI.
     */
    public void close();
}
