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

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        List<Task> tasks = taskList.getTasks();
        if (!tasks.get(index).isDone()) {
            try {
                tasks.get(index).markAsDone(true);
                storage.saveTasksToMemory(tasks);
                ui.confirmDoneTask(index, tasks.get(index).printTask());
            } catch (IOException e) {
                tasks.get(index).markAsDone(false);
                ui.errorSavingChanges();
                throw new IOException();
            }
        } else {
            ui.printCustomMessage("Task is already done!");
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
            if (!taskData.get(index).isIsDone()) {
                taskData.get(index).setIsDone(true);
            } else {
                return uiController.printCustomMessage("Task is already done!\n");
            }
        } catch (IOException e) {
            uiController.errorSavingChanges();
        }
        return uiController.confirmDoneTask(index, taskList.getTasks().get(index).getTaskName());
    }
}
