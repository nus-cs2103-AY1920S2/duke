import java.time.LocalDate;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    public String getType(String wholeCommand) {
        return wholeCommand.split(" ")[0];
    }

    public int getTaskNum(String wholeCommand) {
        return Integer.parseInt(wholeCommand.split(" ")[1]);
    }

    public int numOfParts(String wholeCommand) {
        return wholeCommand.split(" ").length;
    }

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

    public LocalDate getDate(String wholeCommand) {
        String type = getType(wholeCommand);
        if (type.equals("event")) {
            return LocalDate.parse(wholeCommand.substring(6).split(" /at ")[1]);

        } else {
            return LocalDate.parse(wholeCommand.substring(6).split(" /by ")[1]);
        }
    }
}
