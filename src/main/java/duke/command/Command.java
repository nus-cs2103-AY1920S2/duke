package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;


public class Command {
    protected CommandType type;
    protected List<String> details;
    protected boolean isExit;

    public Command(CommandType type) {
        this.type = type;
        this.isExit = false;
    }

    public Command(CommandType type, List<String> details) {
        this.type = type;
        this.details = details;
        this.isExit = false;
    }

    public CommandType getCommandType() {
        return type;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException{
        throw new DukeException("To be implemented in child class");
    }

    public boolean isExitLoop() {
        return isExit;
    }
}
