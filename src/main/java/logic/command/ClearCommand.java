package logic.command;

import commons.Duke;

import static java.util.Objects.requireNonNull;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        duke.getTaskList().clearAll();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
