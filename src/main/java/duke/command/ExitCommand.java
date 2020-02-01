package duke.command;

import duke.command.Command;
import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

public class ExitCommand implements Command {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse("bye ciao adios");
        ui.exitDuke();
    }

}
