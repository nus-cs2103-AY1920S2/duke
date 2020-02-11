package duke.command;

import duke.exception.InvalidCommandException;

public enum Keyword {
    BYE, FIND, LIST, DONE, DELETE, DEADLINE, EVENT, TODO;

    /**
     * The Keyword enum represents the various commands Duke can respond to.
     *
     * @param cmd The input string
     * @return A Keyword enum
     * @throws InvalidCommandException
     */
    public static Keyword getKeyword(String cmd) throws InvalidCommandException {
        cmd = cmd.toLowerCase();
        if (cmd.equals("bye")) {
            return Keyword.BYE;
        } else if (cmd.equals("find")) {
            return Keyword.FIND;
        } else if (cmd.equals("list")) {
            return Keyword.LIST;
        } else if (cmd.equals("done")) {
            return Keyword.DONE;
        } else if (cmd.equals("delete")) {
            return Keyword.DELETE;
        } else if (cmd.equals("deadline")) {
            return Keyword.DEADLINE;
        } else if (cmd.equals("event")) {
            return Keyword.EVENT;
        } else if (cmd.equals("todo")) {
            return Keyword.TODO;
        } else {
            throw new InvalidCommandException();
        }
    }
}