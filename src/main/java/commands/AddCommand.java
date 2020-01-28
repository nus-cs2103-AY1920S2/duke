package commands;

import tasks.Task;
import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

/**
 *  Handles the command when a user adds a new task.
 *  For the execute method, it is to add the task to the tasklist.
 */

public class AddCommand extends Command {
    protected Task taskToAdd;

    public AddCommand(String command, Task taskToAdd) {
        this.command = command;
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.taskToAdd);
        ui.showAddedTask(taskToAdd, tasks.numOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
