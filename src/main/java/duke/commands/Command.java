package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class Command {

    protected String command;
    protected boolean isExit;

    public Command (String command) {
        this.command = command;
        if (command.equals("bye")) {
            isExit = true;
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        if (!isExit()) {
            ui.print("Your entry is not recognized. Please try again.");
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
