package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

public class UnknownCommand implements Command {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse("I don't understand what you want");
    }

}
