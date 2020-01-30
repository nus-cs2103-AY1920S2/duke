package com.duke.command;

import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

import java.io.IOException;

/**
 * Represents a command that adds a task to the task list.
 */
public class TaskCommand extends Command {
    private Task task;

    /**
     * Creates a Task command that adds a task specified to the task list.
     *
     * @param item The Task to be added.
     */
    public TaskCommand(Task item) {
        task = item;
    }

    /**
     * Executes the command that adds a task to the list.
     * @param tasks   the TaskList of the current Duke session.
     * @param ui      the User Interface of the current Duke session.
     * @param storage the storage file of the current Duke session.
     * @throws DukeException when the saving of the file fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.addTask(task);
            storage.save(tasks);
            ui.showAdd(task, tasks.tasks.size());
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}


