package commands;

import tasks.Task;
import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

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
