package duke;

/**
 * Represents a parser to deal with making sense of user command.
 */
public class Parser {

    public Parser() {};

    /**
     * Returns the command keyword based on the user command.
     *
     * @param input The user command input.
     * @return The command keyword.
     */
    public String parseCommand(String input) {
        String command;
        if (input.equals("bye")) {
            command = input;
        } else if (input.equals("list")) {
            command = input;
        } else if (input.indexOf("done") == 0) {
            command = "done";
        } else if (input.indexOf("delete") == 0) {
            command = "delete";
        } else if (input.indexOf("todo") == 0) {
            command = "todo";
        } else if (input.indexOf("deadline") == 0) {
            command = "deadline";
        } else if (input.indexOf("event") == 0) {
            command = "event";
        } else if (input.indexOf("find") == 0) {
            command = "find";
        } else {
            command = "invalid command";
        }
        return command;
    }

}

