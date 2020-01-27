package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

import java.time.LocalDate;

public class CheckDateCommand extends Command {
    private final LocalDate date;

    public CheckDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        ui.respond(() -> {
            ui.speak("These are what you have on this day:");
            for (int i = 1; i <= tasks.taskCount(); i++) {
                if (tasks.getTask(i).occursOn(date)) {
                    ui.speak(String.format("%d.%s", i, tasks.getTask(i)));
                }
            }
        });
    }
}
