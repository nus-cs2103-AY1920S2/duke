package logic.command;

import commons.Duke;

public abstract class Command {
    public abstract CommandResult execute(Duke duke) throws CommandException;
}
