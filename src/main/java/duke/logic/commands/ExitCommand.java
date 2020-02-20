package duke.logic.commands;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }
}
