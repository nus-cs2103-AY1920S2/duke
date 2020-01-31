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
    public int getTaskNum(String wholeCommand) {
        return Integer.parseInt(wholeCommand.split(" ")[1]);
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
    public String getDesc(String wholeCommand) {
        String type = getType(wholeCommand);
        if (type.equals("todo")) {
            return wholeCommand.substring(5);
        } else if (type.equals("event")) {
            return wholeCommand.substring(6).split(" /at ")[0];
        } else {
            return wholeCommand.substring(9).split(" /by ")[0];
        }
    }

    /**
     * Gets the date of the task from the command provided.
     * @param wholeCommand The user command.
     * @return The date.
     */
    public LocalDate getDate(String wholeCommand) {
        String type = getType(wholeCommand);
        if (type.equals("event")) {
            return LocalDate.parse(wholeCommand.substring(6).split(" /at ")[1]);

        } else {
            return LocalDate.parse(wholeCommand.substring(6).split(" /by ")[1]);
        }
    }
}
