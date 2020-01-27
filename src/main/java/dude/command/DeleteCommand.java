package dude.command;

import dude.task.Task;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
