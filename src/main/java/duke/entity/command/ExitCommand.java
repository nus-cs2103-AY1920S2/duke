package main.java.duke.entity.command;

import javafx.collections.ObservableList;
import main.java.duke.entity.TaskList;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage, ObservableList<TaskModel> taskData, UiController uiController) {
        this.execute(taskList, ui, storage);
        return uiController.sayBye();
    }
}
