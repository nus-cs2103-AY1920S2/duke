import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;


public class Parser {

    private static String[] timeRegexes = {
            "H:m", "Hmm", "H.m", "h.m a", "h.m a", "h:m a", "h:m a", "hmm a", "hmma", "h a", "ha", "H"
    };

    private static String[] dateRegexes = {
            "ddMMyyyy", "yyyyMMdd", "d-M-yyyy", "d/M/yyyy", "yyyy-M-d", "yyyy/M/d"
    };

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

    public int getTaskIndex(String input) {
        String[] splitInput = input.split("\\s+");
        return Integer.parseInt(splitInput[splitInput.length - 1]);
    }

    public String getType(String input) {
        return input.split("\\s+")[0];
    }

    public String getDescription(String input) {
        int len = input.split("\\s+")[0].length();
        return input.substring(len).trim();
    }

    public String getTaskName(String input, String regex) {
        return input.split(regex)[0];
    }

    public String getSearchTerm(String input) {
        return input.substring(5).trim();
    }

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
        throw new DukeException("Time"); //thrown if time input is formatted poorly
    }

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

}
