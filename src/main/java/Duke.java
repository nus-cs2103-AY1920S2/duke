import command.Controller;
import command.DukeException;
import command.Storage;
import command.UI;
import java.util.Scanner;

import tasks.TaskList;

/**
 * The project is a product named Duke, a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class Duke {

    private TaskList taskList;

    /**
     * Starts the chat bot by first retrieving saved file from hard disk then
     * obtaining input from the user.
     */
    public void start() {
        taskList = Storage.readFromFile();
    }

    /**
     * Saves the task list into the storage file when user terminates the programme.
     */
    public void end() {
        Storage.saveFile(taskList);
    }

    /**
     * Returns the task list.
     *
     * @return the task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }
}

