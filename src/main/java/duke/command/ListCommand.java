package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

public class ListCommand implements Command {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse(taskList.toString());
    }

}
