package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;

public class HelpCommand extends Command {

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        return ui.showHelpMessage();
    }
}