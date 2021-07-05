package commands;


/** Clears the Task List */

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE_1 = "Clears task list permanently.";
    public static final String MESSAGE_USAGE_2 ="    Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Task List has been cleared!";

    @Override
    public CommandResult execute() {
        taskManager.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult executeForGUI() {
        taskManager.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
