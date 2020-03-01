package logic.command;

import commons.Duke;

import static java.util.Objects.requireNonNull;

public class HelloCommand implements Command {
    public static final String COMMAND_WORD = "hello";
    public static final String MESSAGE_SUCCESS = "Hello! I'm Duke\n\tWhat can I do for you?";

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
