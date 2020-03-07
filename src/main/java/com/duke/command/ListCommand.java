package com.duke.command;

import com.duke.tag.TagList;
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

    public void execute(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        ui.showList(tasks);
    }

    @Override
    public String executeOnGui(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        return ui.getListMessage(tasks);
    }
}