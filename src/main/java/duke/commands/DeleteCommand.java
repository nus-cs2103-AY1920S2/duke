package duke.commands;

import duke.utilities.*;
import duke.tasks.*;
import duke.Ui;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    int[] taskNumbers;

    /**
     * Constructor for delete command. Supports multi-delete using varArgs
     *
     * @param taskNumbers integer array of varied length, depending on number of items the user wants to delete.
     */
    public DeleteCommand(int... taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Deletes task from TaskList object.
     * Updates the storage.
     * Prints Ui's delete message.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return String delete message
     * @throws DukeException
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        ArrayList<Task> deletedTasks = taskList.deleteTasks(taskNumbers);
        storage.update(taskList.getTaskList());
        return ui.deleteMsg(deletedTasks);
    }
}
