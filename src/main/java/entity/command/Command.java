package entity.command;

import entity.TaskList;
import handler.Storage;
import handler.Ui;

public abstract class Command {

    /**
     * Executes the task based on the eventual type
     *
     * @param taskList the current saved state of the TaskList
     * @param ui the interface responsible for interacting with the user
     * @param storage the handler responsible for handling saving/loading from file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Determines whether a program should terminate
     *
     * @return whether to exit program after command has been run
     */
    public abstract boolean isExit();

}
