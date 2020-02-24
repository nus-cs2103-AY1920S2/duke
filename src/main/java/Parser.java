import exception.InvalidCommandException;
import exception.InvalidTaskIndexException;

import java.time.LocalDate;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Gets the type of the task from the command provided.
     * @param wholeCommand The user command.
     * @return The type of the task.
     */
    public String getType(String wholeCommand) {
        return wholeCommand.split(" ")[0];
    }

    /**
     * Gets the task index number from the command provided.
     * @param wholeCommand The user command.
     * @return The task number.
     */
    public int getTaskIndex(String wholeCommand) throws InvalidTaskIndexException {
        String str = wholeCommand.split(" ")[1];
        if (isInteger(str)) {
            return Integer.parseInt(wholeCommand.split(" ")[1]);
        } else {
            throw new InvalidTaskIndexException(":-( index provided should be an integer! :-(");
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Counts the number of parts in the command provided.
     * @param wholeCommand The user command.
     * @return The number of parts.
     */
    public int numOfParts(String wholeCommand) {
        return wholeCommand.split(" ").length;
    }

    /**
     * Gets the description of the task from the command provided.
     * @param wholeCommand The user command.
     * @return The description of the task.
     */
    public String getDesc(String wholeCommand) throws InvalidCommandException {
        String type = getType(wholeCommand);
        if (type.equals("todo")) {
            return wholeCommand.substring(5);
        } else if (type.equals("event")) {
            if (wholeCommand.substring(6).split("/at").length == 1) {
                throw new InvalidCommandException(":-( To create an event you should type something in this format:\n"
                        + "event [event name] /at [yyyy-mm-dd]");
            }
            return wholeCommand.substring(6).split(" /at ")[0];
        } else {
            if (wholeCommand.substring(9).split("/by").length == 1) {
                throw new InvalidCommandException(":-( To create a deadline you should type something in this format:\n"
                        + "deadline [deadline name] /at [yyyy-mm-dd]");
            }
            return wholeCommand.substring(9).split(" /by ")[0];
        }
    }

    /**
     * Gets the date of the task from the command provided.
     * @param wholeCommand The user command.
     * @return The date.
     */
    public LocalDate getDate(String wholeCommand) throws InvalidCommandException {
        String type = getType(wholeCommand);
        if (type.equals("event")) {
            if (checkDateFormat(wholeCommand.substring(6).split(" /at ")[1])) {
                return LocalDate.parse(wholeCommand.substring(6).split(" /at ")[1]);
            } else {
                throw new InvalidCommandException(":-( The format of date must be YYYY-MM-DD :-(");
            }

        } else {
            if (checkDateFormat(wholeCommand.substring(9).split(" /by ")[1])) {
                return LocalDate.parse(wholeCommand.substring(9).split(" /by ")[1]);
            } else {
                throw new InvalidCommandException(":-( The format of date must be YYYY-MM-DD :-(");
            }
        }
    }

    /**
     * Checks the format of the date supplied by the user.
     * @param string The command by the user.
     * @return True if it is correct and false otherwise.
     */
    public boolean checkDateFormat(String string) {
        String[] strings = string.split("-");
        return (strings[0].length() == 4) && (strings[1].length() == 2) && (strings[2].length() == 2);
    }
}
