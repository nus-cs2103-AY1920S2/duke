package com.duke.command;

import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a command that deletes the task at the given index.
     * @param index the task index the command is to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            Task t = tasks.deleteTask(index);
            storage.save(tasks);
            ui.showDelete(t, tasks.tasks.size());
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}
