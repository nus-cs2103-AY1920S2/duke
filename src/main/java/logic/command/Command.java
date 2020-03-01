package logic.command;

import commons.Duke;

public interface Command {
    CommandResult execute(Duke duke) throws CommandException;
}
