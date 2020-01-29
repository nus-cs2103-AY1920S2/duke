package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

public abstract class Command {
    public boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
