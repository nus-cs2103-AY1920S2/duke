package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class Command implements Executable {

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "";
    };
}