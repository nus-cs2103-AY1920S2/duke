package logic.command;

import commons.Duke;
import commons.Messages;
import tasks.TaskList;
import tasks.Todo;

import static java.util.Objects.requireNonNull;
import static logic.parser.CliSyntax.PREFIX_NAME;
import static logic.parser.CliSyntax.PREFIX_TAG;

public class TodoCommand implements Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the task list. "
            + "Parameters: "
            + "todo "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "iP"
            + PREFIX_TAG + "CS2103T"
            + PREFIX_TAG + "Submission";

    public static final String MESSAGE_SUCCESS = "New task added: \n\t%1$s";

    private final Todo todoToAdd;

    /**
     * Creates an TodoCommand to add the specified {@code Todo}
     */
    public TodoCommand(Todo todo) {
        requireNonNull(todo);
        todoToAdd = todo;
    }

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        TaskList taskList = duke.getTaskList();
        taskList.addTask(todoToAdd);
        int totalTasks = taskList.getTotalTasks();
        return new CommandResult(String.format(MESSAGE_SUCCESS, todoToAdd)
                + Messages.printTotalTasks(totalTasks));
    }
}

