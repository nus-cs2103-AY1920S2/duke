package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.NoDateProvidedException;
import duke.exception.NoDescriptionException;
import duke.exception.NoKeywordProvidedException;
import duke.exception.NoTaskNumberException;


/**
 * Parser
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 30 Jan 2020
 *
 * @author Jel
 */
public class Parser {
    private CommandHandler commandHandler;
    private Ui ui;

    /**
     * Constructs a Parser instance.
     */
    public Parser(CommandHandler commandHandler, Ui ui) {
        this.commandHandler = commandHandler;
        this.ui = ui;
    }

    /**
     * Parses user input.
     * @param line The line of input to be parsed.
     */
    public String parseLine(String line) {
        String trimmed = line.trim();
        String cmd = getCommand(trimmed);
        String keyword = "";
        int taskNum = -1;
        String descAndDate = "";

        try {
            switch (cmd) {
            case "bye":
                return ui.bye();
            case "list":
                break;
            case "done": case "delete":
                taskNum = getTaskNum(trimmed);
                break;
            case "find":
                keyword = getKeyword(trimmed);
                break;
            case "todo": case "deadline": case "event":
                descAndDate = getDescAndDate(cmd, trimmed);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            return e.toString();
        }

        return commandHandler.handleCommand(new String[] {cmd, descAndDate}, taskNum, keyword);
    }

    /**
     * Extracts user's command from line of input.
     * @param line The user's line of input.
     * @return The user's command.
     */
    private String getCommand(String line) {
        return line.split(" ", 2)[0].trim();
    }

    /**
     * Extracts the description and date of the task.
     * @param cmd The user's command.
     * @param line The user's line of input.
     * @return A joined string of the description and date of the task.
     * @throws NoDescriptionException No date for the deadline or event was provided.
     * @throws NoDateProvidedException No description for the deadline or event was provided.
     */
    private String getDescAndDate(String cmd, String line) throws NoDescriptionException, NoDateProvidedException {
        String[] arr = line.split(" ", 2);
        if (arr.length < 2) {
            throw new NoDescriptionException();
        }
        if (cmd.equals("todo")) {
            return arr[1].trim();
        } else if (cmd.equals("event")) {
            String[] descAndDate = arr[1].split(" /at ");
            if (descAndDate.length < 2) {
                throw new NoDateProvidedException("at");
            }
            return String.join(" | ", descAndDate);
        } else {
            String[] descAndDate = arr[1].split(" /by ");
            if (descAndDate.length < 2) {
                throw new NoDateProvidedException("by");
            }
            return String.join(" | ", descAndDate);
        }
    }

    /**
     * Gets task number of task to be handled.
     * @param line The line of input from user.
     * @return The tasks' number.
     * @throws NoTaskNumberException No task number was provided for the action to be done.
     */
    private int getTaskNum(String line) throws NoTaskNumberException {
        String[] splitInput = line.split(" ");
        StringBuilder sb = new StringBuilder();
        if (splitInput.length > 1) {
            return Integer.parseInt(splitInput[1]);
        } else {
            throw new NoTaskNumberException();
        }
    }

    /**
     * Gets the keyword that the user is searching for from the line of input.
     * @param line The line of user input.
     * @return The user's search keyword
     * @throws NoKeywordProvidedException No keyword was provided for the find action.
     */
    private String getKeyword(String line) throws NoKeywordProvidedException {
        String[] temp = line.split(" ", 2);
        if (temp.length < 2) {
            throw new NoKeywordProvidedException();
        }
        return temp[1];
    }
}
