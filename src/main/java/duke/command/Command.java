package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TasksList;

public abstract class Command {
    public boolean isExit;

    public Command(){
        isExit = false;
    }
    public abstract void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException;
}
