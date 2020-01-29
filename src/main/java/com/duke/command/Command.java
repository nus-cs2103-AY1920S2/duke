package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

/**
 * An abstract class that represents a command to be executed. Its child classes
 * represents various types of commands in the Duke programme.
 */
public abstract class Command {
    /**
     * a parameter that indicates whether the command is and ExitCommand.
     */
    public boolean isExit = false;

    /**
     * Executes this command on the Duke session specified by the TaskList, Ui, Storage objects
     * associated with it.
     * @param tasks the TaskList of the current Duke session.
     * @param ui the User Interface of the current Duke session.
     * @param storage the storage file of the current Duke session.
     * @throws DukeException happens when there is a problem executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
