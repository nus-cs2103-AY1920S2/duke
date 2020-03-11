package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

import java.time.LocalDate;

public class CheckDateCommand extends Command {
    private final LocalDate date;

    /**
     * Initializes a new CheckDateCommand object which tells users what tasks occur on the given date upon executing.
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
     * @param storage the IStorage to save changes to the task list to disk. Not used by CheckDateCommand.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        ui.respond(() -> {
            ui.speak("These are what you have on this day:");
            tasks.showFilteredTasks(task -> task.occursOn(date))
                    .forEachOrdered(ui::speak);
        });
    }
}
