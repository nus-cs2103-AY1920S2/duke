package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;

public class ByeCommand extends Command {

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        System.out.println(ui.showByeMessage());
        return ui.showByeMessage();
    }
}
