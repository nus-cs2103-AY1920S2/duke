package dude.command;

import dude.task.Task;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class DoneCommand extends Command {
    private final int index;

    /**
     *
     * @param index the index of the task to mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a previously incomplete task (!isDone) as complete and displays the task to users.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage from which the current session was loaded and to which the session will
     *  be saved to on an exiting command.
     * @throws CommandExecutionException If no task exists at that index (index < 1 or index > tasks.taskCount()),
     *  or if the task at given index is already done.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) throws CommandExecutionException {
        try {
            Task completed = tasks.getTask(this.index);
            if (completed.isDone()) {
                throw new CommandExecutionException("That task is already done dude!");
            }
            completed.markAsDone();
            ui.respond("Good job dude! I've marked this task as done:", "  " + completed);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandExecutionException("You don't have such a task, dude!");
        }
    }
}
