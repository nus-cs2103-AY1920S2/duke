package commands;

import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

public class ListCommand extends Command {
    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
