package duke;

import java.io.IOException;

public class ByeCommand implements Command {

    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        storage.newSave(taskList);
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
