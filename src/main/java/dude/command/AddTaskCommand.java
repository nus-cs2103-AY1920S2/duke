package dude.command;

import dude.Task;
import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        tasks.addTask(this.task);
        ui.respond("I gotcha my dude. I've added this task:",
                String.format("  %s", this.task),
                String.format("Now you got %d tasks in your list", tasks.taskCount()));
    }
}
