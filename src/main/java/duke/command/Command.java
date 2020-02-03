package duke.command;

import duke.Storage;
import duke.task.Task;

import java.util.ArrayList;

public abstract class Command {
    public abstract boolean execute(Storage storageController, ArrayList<Task> storage);
}

