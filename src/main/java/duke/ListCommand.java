package duke;

import java.io.IOException;

/**
 * The type List command.
 */
public class ListCommand implements Command {

    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        ui.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
