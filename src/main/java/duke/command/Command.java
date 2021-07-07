package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.gui.Gui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);

    public abstract String execute(TaskList taskList, Gui gui);

    public boolean isExit() {
        return false;
    }
}
