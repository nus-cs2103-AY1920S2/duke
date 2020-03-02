package logic.command;

import commons.Duke;
import logic.parser.CommandSyntax;
import logic.parser.FriendlierSyntax;

import static java.util.Objects.requireNonNull;

public class AliasCommand implements Command {
    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_SUCCESS = "Alias added.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an alias for command.";

    private final FriendlierSyntax friendlierSyntax;

    public AliasCommand(FriendlierSyntax friendlierSyntax) {
        this.friendlierSyntax = friendlierSyntax;
    }

    @Override
    public CommandResult execute(Duke duke) throws CommandException {
        requireNonNull(duke);
        CommandSyntax commandSyntax = duke.getLogicManager().getCommandSyntax();
        if (!commandSyntax.isAliasUnique(friendlierSyntax.getAlias())) {
            throw new CommandException("Alias already in used.");
        } else if (!commandSyntax.isDefaultCommand(friendlierSyntax.getCommand())) {
            throw new CommandException("No such command found!");
        }
        commandSyntax.addFriendlierSyntax(friendlierSyntax);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

