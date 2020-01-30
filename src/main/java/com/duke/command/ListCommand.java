package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

/**
 * Represents a command that queries the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a command that queries the list of tasks.
     */
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks);
    }
}