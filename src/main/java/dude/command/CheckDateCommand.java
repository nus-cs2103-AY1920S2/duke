package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

import java.time.LocalDate;

public class CheckDateCommand extends Command {
    private final LocalDate date;

    /**
     *
     * @param date the date to check current tasks to see if any of them occur on date.
     */
    public CheckDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Shows the user a filtered TaskList for tasks that occur on date.
     * Never throws CommandExecutionException.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage from which the current session was loaded and to which the session will
     *  be saved to on an exiting command.
     */
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
