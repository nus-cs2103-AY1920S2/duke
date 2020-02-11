package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.gui.Gui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        return gui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
