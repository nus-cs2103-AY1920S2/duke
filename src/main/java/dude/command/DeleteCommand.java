package dude.command;

import dude.task.Task;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    /**
     *
     * @param index the index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes task at given index and reports that task's information to users.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage from which the current session was loaded and to which the session will
     *  be saved to on an exiting command.
     * @throws CommandExecutionException If no task exists at that index (index < 1 or index > tasks.taskCount()).
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) throws CommandExecutionException {
        try {
            Task deleted = tasks.removeTask(index);
            ui.respond("I gotcha my dude. I've taken out this task:",
                    String.format("  %s", deleted),
                    String.format("Now you got %d tasks in your list", tasks.taskCount()));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandExecutionException("You don't have such a task, dude!");
        }
    }
}
