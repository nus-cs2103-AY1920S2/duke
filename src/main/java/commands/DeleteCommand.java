package commands;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

import java.io.IOException;

/**
 * Handles the command when the user wants a task deleted.
 * For the execute method, it removes the task from the tasklist.
 */

public class DeleteCommand extends Command {
    protected int taskToDelete;

    public DeleteCommand(String command, int taskToDelete) {
        this.command = command;
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        try {
            if (this.taskToDelete > tasks.numOfTasks()) {
                throw new DukeException("☹ OOPS!!! No task to be deleted found!");
            } else {
                Task taskRemoved = tasks.remove(this.taskToDelete);
                ui.clearResponse();
                ui.showDeletedTask(taskRemoved, tasks.numOfTasks());
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
