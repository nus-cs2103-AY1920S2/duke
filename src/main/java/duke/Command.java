package duke;

public enum Command {
    BYE, FIND, LIST, DONE, DELETE, DEADLINE, EVENT, TODO;

    /**
     * The Command enum represents the various commands Duke can respond to.
     * @param cmd The input string
     * @return A Command enum
     * @throws InvalidCommandException
     */
    public static Command getCommand(String cmd) throws InvalidCommandException {
        cmd = cmd.toLowerCase();
        if (cmd.equals("bye")) {
            return Command.BYE;
        } else if (cmd.equals("find")) {
            return Command.FIND;
        } else if (cmd.equals("list")) {
            return Command.LIST;
        } else if (cmd.equals("done")) {
            return Command.DONE;
        } else if (cmd.equals("delete")) {
            return Command.DELETE;
        } else if (cmd.equals("deadline")) {
            return Command.DEADLINE;
        } else if (cmd.equals("event")) {
            return Command.EVENT;
        } else if (cmd.equals("todo")) {
            return Command.TODO;
        } else {
            throw new InvalidCommandException();
        }
    }
}