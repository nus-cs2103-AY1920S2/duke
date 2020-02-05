package main.java.duke.entity.command;

import javafx.collections.ObservableList;
import main.java.duke.entity.task.Task;
import main.java.duke.entity.TaskList;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;

import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command {

    private int index;
    private Task toBeDeleted;

    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        List<Task> tasks = taskList.getTasks();
        toBeDeleted = tasks.remove(index);
        try {
            storage.saveTasksToMemory(tasks);
            ui.confirmDeleteTask(toBeDeleted, taskList);

        } catch (IOException e) {
            tasks.add(index, toBeDeleted);
            ui.errorSavingChanges();
            throw new IOException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage, ObservableList<TaskModel> taskData, UiController uiController) {
        try {
            this.execute(taskList, ui, storage);
            taskData.remove(index);
        } catch (IOException e) {
            return uiController.errorSavingChanges();
        }
        return uiController.confirmDeleteTask(toBeDeleted, taskList);
    }
}
