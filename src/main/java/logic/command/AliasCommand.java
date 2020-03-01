package logic.command;

import commons.Duke;

import static java.util.Objects.requireNonNull;

public class AliasCommand implements Command {
    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_SUCCESS = "Alias added.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a alias for command. ";

    private final String alias;
    private final String command;

    public AliasCommand(String alias, String command) {
        this.alias = alias;
        this.command = command;
    }

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        duke.getFriendlierSyntax().addAlias(alias, command);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

