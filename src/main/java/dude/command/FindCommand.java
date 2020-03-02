package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initializes a new FindCommand object which tells users what tasks match the given keyword upon executing.
     *
     * @param keyword the word(s) to match against the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Shows the user a filtered TaskList for tasks that match the given keyword.
     * Never throws CommandExecutionException.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage to save changes to the task list to disk. Not used by FindCommand.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        ui.respond(() -> {
            ui.speak("These are the matching tasks I found:");
            tasks.showFilteredTasks(task -> task.getDetails().contains(keyword))
                    .forEachOrdered(ui::speak);
        });
    }
}
