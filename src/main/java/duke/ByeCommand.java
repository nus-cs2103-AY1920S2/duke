package duke;

import java.io.IOException;

/**
 * The type Bye command.
 */
public class ByeCommand implements Command {

    @Override
    public String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        storage.newSave(taskList);
        return ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
