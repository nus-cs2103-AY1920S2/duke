package main.java.duke.entity.command;

import javafx.collections.ObservableList;
import main.java.duke.entity.TaskList;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;

import java.io.IOException;

public abstract class Command {


    /**
     * Executes the task based on the eventual type
     *
     * @param taskList the current saved state of the TaskList
     * @param ui the interface responsible for interacting with the user
     * @param storage the duke.handler responsible for handling saving/loading from file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Determines whether a program should terminate
     *
     * @return whether to exit program after command has been run
     */
    public abstract boolean isExit();

    public abstract String execute(TaskList taskList, Ui ui, Storage storage, ObservableList<TaskModel> taskData, UiController uiController);


}
