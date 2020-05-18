/** Parses user input where necessary. */
public class Parser {

    /**
     * Generates a command word for all user input keywords.
     *
     * @param getInput user keyword input.
     * @return command for next action.
     */
    public Command parse(String getInput) {
        Command command = null;
        if (getInput.equals("todo") || getInput.equals("deadline")
                || getInput.equals("event") || getInput.equals("note")) {
            command = Command.ADD;
        } else if (getInput.equals("bye")) {
            command = Command.BYE;
        } else if (getInput.equals("help")) {
            command = Command.HELP;
        } else if (getInput.equals("list")) {
            command = Command.LIST;
        } else if (getInput.equals("find")) {
            command = Command.FIND;
        } else if (getInput.equals("done")) {
            command = Command.DONE;
        } else if (getInput.equals("delete")) {
            command = Command.DELETE;
        }
        return command;
    }

}
