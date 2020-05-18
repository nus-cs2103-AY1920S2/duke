import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

/**
 * <h1> Parser </h1>
 * Parser deals with making sense of the user command
 */

public class Parser {

    private static String[] timeRegexes = {
            "H:m", "Hmm", "H.m", "h.m a", "h.m a", "h:m a", "h:m a", "hmm a", "hmma", "h a", "ha", "H"
    };

    private static String[] dateRegexes = {
            "ddMMyyyy", "yyyyMMdd", "d-M-yyyy", "d/M/yyyy", "yyyy-M-d", "yyyy/M/d"
    };

    /**
     * This method determines whether the user command is a "Done" or "Delete" command
     * @param input User command string
     * @return a boolean value of whether it is a Done or Detele command
     * @throws DukeException if there is any error in the user command
     */
    //@@author BransonNg - reused
    // Previously written code by me did not abide single responsibility principle. Thus, referred to BransonNg's code
    //for how to re-write it and also how to implement regex.

    public boolean isDoneOrDelete(String input) throws DukeException {
        String doneRegex =
                "(^(done|delete)\\s+.*|.*\\s+(done|delete)$)";
        String actionWithNumRegex = "^(done|delete)\\s+\\d+$";
        String actionAtFrontRegex =  "^(done|delete)\\s+.*";

        if(Pattern.matches(doneRegex, input)) {
            if(!Pattern.matches(actionAtFrontRegex, input)) {
                throw new DukeException("Command should be placed at the front!");
            } else if (!Pattern.matches(actionWithNumRegex, input)) {
                throw new DukeException("Please provided the task number!");
            }
            return true;
        }
        return false;
    }

    /**
     * This method determines whether the user command is a "Find" command
     * @param input User command string
     * @return a boolean value of whether it is a "Find" command
     * @throws DukeException if there is any error in the user command
     */
    public boolean isFind(String input) throws DukeException{
        String findRegex =
                "(^(find)\\s+.*|.*\\s+(find)$|^(find))";
        String withSearchTermRegex = "^(find)\\s+.*";
        if(Pattern.matches(findRegex, input)) {
            if(!Pattern.matches(withSearchTermRegex, input)) {
                throw new DukeException("Search term must be provided!");
            }
            return true;
        }
        return false;
    }

    public boolean isEdit(String input) throws DukeException{
        String editRegex =
                "(^(edit)\\s+.*|.*\\s+(find)$|^(edit))";
        String withEditTerm = "^(edit)\\s+.*";
        if(Pattern.matches(editRegex, input)) {
            if(!Pattern.matches(withEditTerm,input)) {
                throw new DukeException("Desired Task number should be provided!");
            }
            return true;
        }
        return false;
    }

    /**
     * This method returns the task index in a user command string
     * @param input user command string
     * @return Task index integer
     */

    public int getTaskIndex(String input) {
        String[] splitInput = input.split("\\s+");
        return Integer.parseInt(splitInput[splitInput.length - 1]);
    }

    /**
     * This method returns the type of the user command
     * @param input user command string
     * @return type string
     */
    public String getType(String input) {
        return input.split("\\s+")[0];
    }

    /**
     * returns the description (including time and date) provided by user for the task
     * @param input user command string
     * @return task description string
     */

    public String getDescription(String input) {
        int len = input.split("\\s+")[0].length();
        return input.substring(len).trim();
    }

    /**
     * returns the name of the task
     * @param input user command string
     * @param regex regex depending on the type of task
     * @return name of task string
     */
    public String getTaskName(String input, String regex) {
        return input.split(regex)[0];
    }

    /**
     * returns the search term of a "Find" command
     * @param input User command string
     * @return search term string
     */

    public String getSearchTerm(String input) {
        return input.substring(5).trim();
    }

    /**
     * returns date in LocalDate of user command
     * @param dateTime string containing both the date and time
     * @return date in type LocalDate
     * @throws DukeException if there is any error in the format of input
     */
    public static LocalDate getDate(String dateTime) throws DukeException {
        String date = dateTime.split(" ")[0].trim();
        for (String pattern : Parser.dateRegexes) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception err) {
                continue;
            }
        }
        throw new DukeException(
                        "Missing time/date information %n please provide date then time separated by a space");
    }

    /**
     * returns time in LocalTime of user command
     * @param dateTime string containing both the date and time
     * @return time in type LocalTime
     * @throws DukeException if there is any error in the format of input
     */
    public static LocalTime getTime(String dateTime) throws DukeException {
        String time;
        try {
            String[] split = dateTime.split(" ");
            time = String.join(" ", Arrays.copyOfRange(split, 1, split.length))
                    .trim()
                    .toUpperCase();
        } catch (Exception e) {
            throw new DukeException(
                    String.format(
                            "Missing time/date information %n please provide date then time separated by a space"));
        }

        for (String pattern : Parser.timeRegexes) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception err) {
                continue;
            }
        }
        throw new DukeException("Time input is formatted poorly");
    }

    /**
     * returns a string of the date and time information from the user command
     * @param description description of task provided by user
     * @param regex regex depending on task type
     * @return string containing date and time information
     * @throws DukeException if there is any error in the format of user command
     */
    public static String getDateTime(String description, String regex) throws DukeException {
        Matcher matcher = Pattern.compile(regex).matcher(description);
        int index = matcher.find() ? matcher.start() : -1;
        if (index == -1) {
            throw new DukeException(String.format("Please provide %s for this event type", regex));
        }
        String dateTime = description.substring(index + regex.length()).trim();

        if (dateTime.length() == 0) throw new DukeException("Please provide a time");

        return dateTime;
    }

    //@@author
}
