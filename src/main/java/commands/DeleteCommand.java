package commands;

import tasks.Task;
import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskRemoved = tasks.remove(this.taskToDelete);
        ui.showDeletedTask(taskRemoved, tasks.numOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
