package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.done(this.index, storage);
    }

    public boolean isExit() {
        return false;
    }

}