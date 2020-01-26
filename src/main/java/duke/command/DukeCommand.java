package duke.command;

import duke.exception.InvalidCommandException;

public enum DukeCommand {
    BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT;

    public static DukeCommand getCommand (String command) throws InvalidCommandException {
        if (command.equals("bye")) {
            return DukeCommand.BYE;
        } else if (command.equals("list")) {
            return DukeCommand.LIST;
        } else if (command.equals("done")) {
            return DukeCommand.DONE;
        } else if (command.equals("delete")) {
            return DukeCommand.DELETE;
        } else if (command.equals("todo")) {
            return DukeCommand.TODO;
        } else if (command.equals("deadline")) {
            return DukeCommand.DEADLINE;
        } else if (command.equals("event")) {
            return DukeCommand.EVENT;
        } else {
            throw new InvalidCommandException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
