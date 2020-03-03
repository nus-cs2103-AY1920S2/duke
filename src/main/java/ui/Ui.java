package ui;

import exceptions.DukeException;
//import org.junit.Test;
import tasks.Task;
import tasks.TaskList;

import java.util.List;

/**
 * Ui class interacts with Duke user, requesting input and printing prompts for user.
 * This class provides constructor for Ui object.
 */
public class Ui {
    private static final String BORDER = "**********************************************************************";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String TASK_ADD_STRING = "Noted. The following task has been added:\n   ";
    private static final String BYE_MESSAGE = "\n Goodbye. I hope I was useful. See you again.\n";
    private String lastMessage;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
    }

    /**
     * Ui object displays welcome message when Duke is launched.
     */
    public String showWelcome() {
        String greeting = "Hi! My name is Alfred. Welcome to\n";
        return BORDER + "\n  " + greeting +  LOGO + "\n" + BORDER;
    }

    /**
     * Ui object displays input String.
     * @param toPrint input String for Ui object to display.
     */
    public String formatResponse(String toPrint) {
        return BORDER + toPrint + BORDER;
    }

    /**
     * Fetches Duke's response to 'list' command.
     * @return Duke's response.
     */
    public String getListResponse(TaskList tasks) {
        if (tasks.isEmpty()) {
            return this.formatResponse("\n  There are currently no items in your list. \n");
        } else {
            return this.formatResponse(tasks.toString());
        }
    }

    /**
     * Retrieves message from Duke when a task has been marked as done.
     * @param task Task that was requested to be done.
     * @return Done Message from Duke.
     */
    public String showTaskDoneMessage(Task task) {
        assert task.isDone() : "Task should be marked as done.";
        String response = "\n  " + "Well Done! The task has been marked as done."
                + "\n  " + task + "\n";
        lastMessage = response;
        return this.formatResponse(response);
    }

    /**
     * Retrieves exit message from Duke when user exits Duke.
     * @return Exit message.
     */
    public String showByeMessage() {
        return this.formatResponse(BYE_MESSAGE);
    }

    /**
     * Retrieves response from Duke when a task is deleted.
     * @param removedTask Task that has been removed.
     * @param deleteIndex Index of task deleted.
     * @param numOfTask Number of tasks remaining in the list.
     * @return Duke's response when a delete command has been inputted by the user.
     */
    public String showDeleteMessage(Task removedTask, int deleteIndex, int numOfTask) {
        String response = "\n  Noted. Task " + deleteIndex + " has been removed:\n  "
                + removedTask + "\n  There are currently " + numOfTask
                + " tasks in the list.\n";
        lastMessage = response;
        return this.formatResponse(response);
    }

    /**
     * Fetches Duke's response to 'find' command.
     * @param key Search parameter.
     * @return Duke's response.
     * @throws DukeException Duke's error message when details of input are incorrect.
     */
    public String getFindResponse(String key, TaskList taskList) throws DukeException {
        if (key.isEmpty()) {
            throw new DukeException(this.formatResponse("\n  My apologies, "
                    + "search parameter seems to be empty.\n  "
                    + "Please check and try again\n. "));
        }

        // Retrieve Task objects with description matching user's description.
        List<Task> results = taskList.find(key);
        if (results.size() == 0) {
            throw new DukeException(this.formatResponse("\n  My apologies, "
                    + "there are no tasks matching '" + key + "' :(\n"));
        }
        String resultTasks = new TaskList(results).toString();
        return this.formatResponse(resultTasks);
    }


    /**
     * Ui object displays error message if any error arises when loading from save file
     * from hard disk.
     */
    public void showLoadingError() {
        System.out.println();
    }

    /**
     * Retrieves Duke's response when a new Task has been added by the user.
     * @param numOfTask Number of tasks currently in the list.
     * @param task New Task being added.
     * @return Duke's response.
     */
    public String showTaskAddMessage(int numOfTask, String task) {
        String response = "\n  " + TASK_ADD_STRING + task + "\n  There are currently "
                + numOfTask + " task(s) in the list.\n";
        this.lastMessage = response;
        return this.formatResponse(response);
    }

    /**
     * Retrieves Duke's response when the undo command is invoked.
     * @return Duke's response.
     */
    public String showUndoMessage() {
        return this.formatResponse("\n Your previous command:\n"
                + this.lastMessage + "\n has been undone.\n");
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
