package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * The Command program calls the method relevant to the command.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class Command {

    protected Storage storage;
    protected TaskList taskList;

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public Command(Storage storage, TaskList taskList) {

        this.storage = storage;
        this.taskList = taskList;

    }

    /**
     * Calls method relevant to the command.
     *
     * @param taskDescriptionArr is a parsed task description which
     *                           contains information such as date
     *                           of task.
     */
    public String executeCommand(String[] taskDescriptionArr) {

        return this.executeCommand(taskDescriptionArr);

    }
}
