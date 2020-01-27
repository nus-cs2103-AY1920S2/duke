package dude.command;

import dude.Task;
import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

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
