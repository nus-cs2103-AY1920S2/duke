package duke.commands;

import duke.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class HelpCommand extends Command {
    public HelpCommand() {

    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.helpMsg();
    }
}
