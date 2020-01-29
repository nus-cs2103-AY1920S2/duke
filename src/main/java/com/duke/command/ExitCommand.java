package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }
}
