package logic.command;

import commons.Duke;
import commons.Messages;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

import static java.util.Objects.requireNonNull;
import static logic.parser.CliSyntax.PREFIX_DATE;
import static logic.parser.CliSyntax.PREFIX_NAME;
import static logic.parser.CliSyntax.PREFIX_TAG;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + "deadline "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_DATE + "DATE]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "iP "
            + PREFIX_DATE + "2020-03-02 "
            + PREFIX_TAG + "CS2103T "
            + PREFIX_TAG + "Submission";

    public static final String MESSAGE_SUCCESS = "New task added: \n\t%1$s";

    private final Deadline deadlineToAdd;

    /**
     * Creates an DeadlineCommand to add the specified {@code Deadline}.
     */
    public DeadlineCommand(Deadline task) {
        requireNonNull(task);
        deadlineToAdd = task;
    }

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        TaskList taskList = duke.getTaskList();
        taskList.addTask(deadlineToAdd);
        int totalTasks = taskList.getTotalTasks();
        return new CommandResult(String.format(MESSAGE_SUCCESS, deadlineToAdd)
                + Messages.printTotalTasks(totalTasks));
    }
}
