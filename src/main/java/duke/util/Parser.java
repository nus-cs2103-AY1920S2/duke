package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.NoDateProvidedException;
import duke.exception.NoDescriptionProvidedException;
import duke.exception.NoKeywordProvidedException;
import duke.exception.NoTaskNumberException;

/**
 * Parses all user input for Duke to handle as commands.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 11 Feb 2020
 *
 * @author Jel
 */
public class Parser {
    private CommandHandler commandHandler;
    private Ui ui;

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
                // this case is needed for GUI
                return ui.bye();
            case "list": case "help":
                break;
            case "done": case "delete": case "unmark":
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
            StringBuilder sb = new StringBuilder("");
            sb.append(e).append("\n");
            return sb.toString();
        }
        return commandHandler.handleCommand(new String[] {cmd, descAndDate}, taskNum, keyword);
    }

    private String getCommand(String line) {
        return line.split(" ", 2)[0].trim();
    }


    private String getDescAndDate(String cmd, String line) throws NoDescriptionProvidedException,
              NoDateProvidedException {
        // TODO: throw NoDescriptionException when only date and task type is provided.
        String[] arr = line.split(" ", 2);
        if (arr.length < 2) {
            throw new NoDescriptionProvidedException();
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

    private int getTaskNum(String line) throws NoTaskNumberException {
        String[] splitInput = line.split(" ");
        StringBuilder sb = new StringBuilder();
        if (splitInput.length > 1) {
            return Integer.parseInt(splitInput[1]);
        } else {
            throw new NoTaskNumberException();
        }
    }

    private String getKeyword(String line) throws NoKeywordProvidedException {
        String[] temp = line.split(" ", 2);
        if (temp.length < 2) {
            throw new NoKeywordProvidedException();
        }
        return temp[1];
    }
}
