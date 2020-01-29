package seedu.duke;

public class Parser {
    public Parser() {

    }

    /**
     * Deals with making sense of the user commands.
     *
     * @param command user's command input
     * @return the processed command
     */
    public String processCommand(String command) {
        try {
            if (command.equalsIgnoreCase("todo")) {
                return Ui.TODO;
            } else if (command.equalsIgnoreCase("deadline")) {
                return Ui.DEADLINE;
            } else if (command.equalsIgnoreCase("event")) {
                return Ui.EVENT;
            } else if (command.equalsIgnoreCase("list")) {
                return Ui.LIST;
            } else if (command.equalsIgnoreCase("find")) {
                return Ui.FIND;
            } else if (command.equalsIgnoreCase("done")) {
                return Ui.DONE;
            } else if (command.equalsIgnoreCase("delete")) {
                return Ui.DELETE;
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
