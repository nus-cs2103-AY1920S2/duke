package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.delete(this.index, storage);
        tasks.printSize();
    }

    public boolean isExit() {
        return false;
    }

}