package entity.command;

import entity.task.Task;
import entity.TaskList;
import handler.Storage;
import handler.Ui;

import java.io.IOException;
import java.util.List;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        tasks.add(newTask);
        try {
            storage.saveTasksToMemory(tasks);
            ui.confirmAddTask(newTask, taskList);
        } catch (IOException e) {
            tasks.remove(tasks.size() - 1);
            ui.errorSavingChanges();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }
}
