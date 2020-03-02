package logic.command;

import commons.Duke;
import commons.Messages;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import static java.util.Objects.requireNonNull;
import static logic.parser.CliSyntax.PREFIX_DATE;
import static logic.parser.CliSyntax.PREFIX_NAME;
import static logic.parser.CliSyntax.PREFIX_TAG;

public class EventCommand implements Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_DATE + "DATE]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "iP"
            + PREFIX_DATE + "2020-03-02"
            + PREFIX_TAG + "CS2103T"
            + PREFIX_TAG + "Submission";

    public static final String MESSAGE_SUCCESS = "New task added: \n\t%1$s";

    private final Task eventToAdd;

    /**
     * Creates an EventCommand to add the specified {@code Event}
     */
    public EventCommand(Task task) {
        requireNonNull(task);
        eventToAdd = task;
    }

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        TaskList taskList = duke.getTaskList();
        taskList.addTask(eventToAdd);
        int totalTasks = taskList.getTotalTasks();
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd)
                + Messages.printTotalTasks(totalTasks));
    }
}

