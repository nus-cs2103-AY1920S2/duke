package dude.command;

import dude.task.Task;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Initializes a new AddTaskCommand object which adds given task to Dude's TaskList upon executing.
     *
     * @param task the Task to be added (Todo, Deadline, Event).
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to current session's tasks.
     * Never throws CommandExecutionException.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage from which the current session was loaded and to which the session will
     *                be saved to on an exiting command.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        tasks.addTask(this.task);
        ui.respond("I gotcha my dude. I've added this task:",
                String.format("  %s", this.task),
                String.format("Now you got %d tasks in your list", tasks.taskCount()));
    }
}
