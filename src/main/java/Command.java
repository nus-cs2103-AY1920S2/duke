public enum Command {
    BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT;

    public static Command getCommand (String command) throws InvalidCommandException {
        if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("list")) {
            return Command.LIST;
        } else if (command.equals("done")) {
            return Command.DONE;
        } else if (command.equals("delete")) {
            return Command.DELETE;
        } else if (command.equals("todo")) {
            return Command.TODO;
        } else if (command.equals("deadline")) {
            return Command.DEADLINE;
        } else if (command.equals("event")) {
            return Command.EVENT;
        } else {
            throw new InvalidCommandException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
