package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.printList();
    }

    public boolean isExit() {
        return false;
    }

}