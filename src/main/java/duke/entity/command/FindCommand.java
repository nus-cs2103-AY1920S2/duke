package duke.entity.command;

import javafx.collections.ObservableList;
import duke.entity.TaskList;
import duke.entity.task.Task;
import duke.gui.TaskModel;
import duke.gui.view.UiController;
import duke.handler.Storage;
import duke.handler.Ui;

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
