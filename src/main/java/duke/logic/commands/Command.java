package duke.logic.commands;

import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {

    protected String commandWord;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "";
    }
}
