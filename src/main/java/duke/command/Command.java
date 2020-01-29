package duke.command;

import duke.task.*;
import duke.other.*;

public abstract class Command {
    public boolean isExit;
    public Command() {
        isExit = false;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
