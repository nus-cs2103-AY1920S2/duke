package commons;

import logic.LogicManager;
import storage.Storage;
import tasks.TaskList;

import java.io.IOException;

/**
 * The project is a product named Duke, a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class Duke {

    private TaskList taskList;
    private LogicManager logicManager;
    private Storage storage;

    /**
     * Starts the chat bot by first retrieving saved file from hard disk then
     * obtaining input from the user.
     */
    public void start() {
        storage = new Storage();
        taskList = storage.readTaskList();
        logicManager = new LogicManager(this, storage);
    }

    /**
     * Saves the task list into the storage file when user terminates the programme.
     */
    public void end() throws IOException {
        storage.saveTaskList(taskList);
        storage.saveFile(logicManager.getCommandSyntax());
    }

    /**
     * Returns the task list.
     *
     * @return the task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the controller.
     *
     * @return the controller.
     */
    public LogicManager getLogicManager() {
        return logicManager;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}

