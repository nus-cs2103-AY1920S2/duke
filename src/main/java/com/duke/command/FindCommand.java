package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

/**
 * Represents a command that finds all tasks whose description contains a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a command that finds all tasks whose description contains a given keyword.
     * @param keyword The keyword that is used to search tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showFind(tasks.findTask(keyword));
    }
}
