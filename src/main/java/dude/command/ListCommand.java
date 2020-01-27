package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        if (tasks.taskCount() == 0) {
            ui.respond("You got nothing to do, dude. Ain't that awesome??");
            return;
        }

        ui.respond(() -> {
            ui.speak("These are your tasks, dude:");
            for (String t : tasks.showAllTasks()) {
                ui.speak(t);
            }
        });
    }
}
