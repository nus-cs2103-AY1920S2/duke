package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getExitMessage();
    }
}
