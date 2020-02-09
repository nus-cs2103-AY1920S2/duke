package parser;

import exception.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import task.Constant;

public class Parser {
    /**
     * @param input raw input
     * @return Boolean returns true if input is a done/delete command
     * @throws DukeException if user input matches a done/delete command but it not properly
     *     formatted
     */
    private static String[] timeRegex = {
        "H:m", "Hmm", "H.m", "h.m a", "h.m a", "h:m a", "h:m a", "hmm a", "hmma", "h a", "ha", "H"
    };

    private static String[] dateRegex = {
        "ddMMyyyy", "yyyyMMdd", "d-M-yyyy", "d/M/yyyy", "yyyy-M-d", "yyyy/M/d"
    };

    public static Boolean isDoneDelete(String input) throws DukeException {
        if (Pattern.matches(
                "(^(done|delete)\\s+.*|(.*\\s+(done|delete)\\s+.*)|.*\\s+(done|delete)$)", input)) {
            if (!Pattern.matches("^(done|delete)\\s+.*", input)) {
                throw new DukeException("Action should be at the front");
            }
            if (Pattern.matches("^(done|delete)\\s+\\d+\\D+$", input)) {
                throw new DukeException("Must end with a number and provide only one number!");
            }
            if (!Pattern.matches("^(done|delete)\\s+\\d+$", input)) {
                throw new DukeException("A task number must be provided");
            }
            return true;
        }
        return false;
    }

    /**
     * @param input raw user input
     * @return Boolean returns true if command is a find command
     * @throws DukeException if user input matches a find command but it not properly formatted
     */
    public static Boolean isFind(String input) throws DukeException {
        if (Pattern.matches("^(find\\s+.*)|(.*\\s+find\\s+.*)|(.*\\s+find$)", input)) {
            if (!Pattern.matches("^find\\s+.*", input)) {
                throw new DukeException("Action should be at the front");
            }
            if (!Pattern.matches("^find\\s+.*", input)) {
                throw new DukeException("A task number must be provided");
            }
            return true;
        }
        return false;
    }

    /**
     * @param input raw input
     * @return String the lower cased task type is returned (todo/deadline/event)
     * @throws DukeException when type is not recognized
     */
    public static String getType(String input) throws DukeException {
        String lowerCaseInput = input.toLowerCase();
        String acceptedTypes = String.format("(%s)", String.join("|", Constant.taskTypes));
        if (Pattern.matches(
                String.format(
                        "^%s\\s+.*|.*\\s+%s$|.*\\s+%s\\s+.*",
                        acceptedTypes, acceptedTypes, acceptedTypes),
                lowerCaseInput)) {
            if (Pattern.matches(String.format("^%s\\s+.*", acceptedTypes), lowerCaseInput)) {
                return input.split(" ")[0].toLowerCase();
            } else {
                throw new DukeException("Please start with event type");
            }
        }
        throw new DukeException("No accepted types present");
    }

    /**
     * @param description user input without the type
     * @return String returns the content
     * @throws DukeException when content is empty, raise exception
     */
    public static String getContent(String description) throws DukeException {
        Matcher matcher = Pattern.compile("(/by|/at)").matcher(description);
        int index = matcher.find() ? matcher.start() : -1;
        if (index == -1 && description.trim().length() > 0) {
            return description.trim();
        }
        String content = description.substring(0, index).trim();
        if (content.length() > 0) return content;
        throw new DukeException("Content cannot be empty!");
    }

    /**
     * @param description user input without type
     * @param regex varies on whether it is event or deadline (/at or /by)
     * @return String raw datetime string
     * @throws DukeException if no datetime string is provided, raise exception
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

    public static String[] getStartEndDateTime(String description, String regex1, String regex2)
            throws DukeException {
        Matcher matcher = Pattern.compile(regex1).matcher(description);
        int index = matcher.find() ? matcher.start() : -1;
        Matcher matcher2 = Pattern.compile(regex2).matcher(description);
        int index2 = matcher2.find() ? matcher2.start() : -1;
        if (index == -1 || index2 == -1) {
            throw new DukeException(
                    String.format("Please provide %s and %s for this event type", regex1, regex2));
        }
        String dateTime = description.substring(index + regex1.length(), index2).trim();
        String dateTime2 = description.substring(index2 + regex2.length()).trim();

        if (dateTime.length() == 0 || dateTime2.length() == 0)
            throw new DukeException("Please provide a time");
        String returnResult[] = {dateTime, dateTime2};
        return returnResult;
    }

    /**
     * @param description input without type
     * @param regex varies on whether it is event or deadline (/at or /by)
     * @return LocalTime turn String time to LocalTime
     * @throws DukeException if time input is poorly formatted
     */
    public static LocalTime getTime(String dateTime) throws DukeException {
        String time;
        try {
            String[] split = dateTime.split(" ");
            time =
                    String.join(" ", Arrays.copyOfRange(split, 1, split.length))
                            .trim()
                            .toUpperCase();
        } catch (Exception e) {
            throw new DukeException(
                    String.format(
                            "Missing time/date information %n please provide date then time separated by a space"));
        }

        for (String pattern : Parser.timeRegex) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException err) {
                continue;
            }
        }
        throw new DukeException("Time is in wrong format");
    }

    /**
     * @param description input without type
     * @param regex varies on whether it is event or deadline (/at or /by)
     * @return LocalDate turn String date to LocalDate
     * @throws DukeException if date input is poorly formatted
     */
    public static LocalDate getDate(String dateTime) throws DukeException {
        String date = dateTime.split(" ")[0].trim();
        for (String pattern : Parser.dateRegex) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException err) {
                continue;
            }
        }
        throw new DukeException("Date is in wrong format");
    }
}
