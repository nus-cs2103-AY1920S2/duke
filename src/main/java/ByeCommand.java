package duke.commands;

import duke.commands.Command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }

}