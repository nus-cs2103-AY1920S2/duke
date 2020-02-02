package duke.commands;
import duke.utilities.*;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) {
        ui.exitMsg();
        return false; // return false to exit
    }
}
