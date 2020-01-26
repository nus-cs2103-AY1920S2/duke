package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        storage.saveSession(ui, tasks);
        ui.respond("See ya!");
        ui.close();
    }
}
