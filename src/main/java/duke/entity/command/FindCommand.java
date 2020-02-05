package main.java.duke.entity.command;

import javafx.collections.ObservableList;
import main.java.duke.entity.TaskList;
import main.java.duke.entity.task.Task;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    private String keyword;
    private List<Task> result;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTaskName().contains(keyword)) {
                result.add(tasks.get(i));
            }
        }
        ui.listFoundTasks(result);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage, ObservableList<TaskModel> taskData, UiController uiController) {
        this.execute(taskList, ui, storage);
        return uiController.listFoundTasks(result);
    }
}
