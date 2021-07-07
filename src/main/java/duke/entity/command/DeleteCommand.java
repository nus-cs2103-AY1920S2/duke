package duke.entity.command;

import javafx.collections.ObservableList;
import duke.entity.task.Task;
import duke.entity.TaskList;
import duke.gui.TaskModel;
import duke.gui.view.UiController;
import duke.handler.Storage;
import duke.handler.Ui;

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
        assert index < tasks.size() : "index should not be larger than size of list";
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
