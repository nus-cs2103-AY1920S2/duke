package parser;

import exceptions.IllegalDateTimeFormatException;
import exceptions.NoCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implements methods to parse the input command.
 */
public class Parser {
    public static final String DATE_TIME_KEY = "(\\d{4}-\\d{2}-\\d{2})?\\s*(\\d{2}:\\d{2})?";

    private static final String DEFAULT_TIME = "23:59";
    private static final String EXIT_KEY = "bye";
    private static final String  VIEW_LIST_KEY = "list";
    private static final String DELETE_KEY = "(delete)(\\s+)(\\d+)";
    private static final String FINISH_KEY = "(done)(\\s+)(\\d+)";
    private static final String TODO_KEY = "(todo)(.*)";
    private static final String DEADLINE_KEY = "(deadline)\\s*(\\S*)\\s*/by\\s*" + DATE_TIME_KEY;
    private static final String EVENT_KEY = "(event)\\s*(\\S*)\\s*/at\\s*" + DATE_TIME_KEY;
    private static final String FIND_KEY = "(find)\\s*(\\S*)";

    private static final Pattern DELETE_PATTERN = Pattern.compile(DELETE_KEY);
    private static final Pattern FINISH_PATTERN = Pattern.compile(FINISH_KEY);
    private static final Pattern TODO_PATTERN = Pattern.compile(TODO_KEY);
    private static final Pattern DEADLINE_PATTERN = Pattern.compile(DEADLINE_KEY);
    private static final Pattern EVENT_PATTERN = Pattern.compile(EVENT_KEY);
    private static final Pattern FIND_PATTERN = Pattern.compile(FIND_KEY);

    /**
     * static method converts string to date time object.
     * @param dateString A valid string representing the date.
     * @param timeString A valid string representing the time.
     * @return LocalDateTime object.
     * @throws IllegalDateTimeFormatException if the string is invalid.
     */
    public static LocalDateTime parseDateTime(String dateString, String timeString) throws
            IllegalDateTimeFormatException {
        //TODO: add a notification
        try {
            LocalDate date = LocalDate.parse(dateString);
            LocalTime time = LocalTime.parse(timeString == null ? DEFAULT_TIME : timeString);
            return date.atTime(time);
        } catch (DateTimeParseException dte) {

            //TODO: change the error message
            throw new IllegalDateTimeFormatException(dte.getMessage() + '\n');
        } catch (NullPointerException npe) {
            throw new IllegalDateTimeFormatException("No date time string being inputted.\n");
        }
    }

    /**
     * Parse the indicated position from the input command.
     * @param pattern Java regular expression pattern.
     * @param input String input.
     * @return parsed position as integer.
     */
    private int findIndex(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return Integer.parseInt(matcher.group(3)) - 1;
    }

    /**
     * Parse the description from the input command.
     * @param pattern Java regular expression pattern.
     * @param input String input.
     * @return parsed description as string.
     */
    private String findDescription(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(2).trim();
    }

    /**
     * Parse the date time from the command to LocalDateTime object.
     * @param pattern Java regular expression pattern.
     * @param input String user input.
     * @return LocalDateTime object.
     * @throws IllegalDateTimeFormatException If the string in invalid format.
     */
    private LocalDateTime findDateTime(Pattern pattern, String input) throws IllegalDateTimeFormatException {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String dateString = matcher.group(3);
        String timeString = matcher.group(4);

        return parseDateTime(dateString, timeString);
    }

    private static boolean isExitKey(String input) {
        return EXIT_KEY.equals(input);
    }

    private String findKeyword(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(2);
    }

    private static boolean isViewListKey(String input) {
        return VIEW_LIST_KEY.equals(input);
    }

    private static boolean isDeleteKey(String input) {
        Matcher deleteMatcher = DELETE_PATTERN.matcher(input);
        return deleteMatcher.find();
    }

    private static boolean isFinishKey(String input) {
        Matcher finishMatcher = FINISH_PATTERN.matcher(input);
        return finishMatcher.find();
    }

    private static boolean isTodoKey(String input) {
        Matcher todoMatcher = TODO_PATTERN.matcher(input);
        return todoMatcher.find();
    }

    private static boolean isDeadLineKey(String input) {
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(input);
        return deadlineMatcher.find();
    }

    private static boolean isEventKey(String input) {
        Matcher eventMatcher = EVENT_PATTERN.matcher(input);
        return eventMatcher.find();
    }

    private static boolean isFindKey(String userInput) {
        Matcher findMatcher = FIND_PATTERN.matcher(userInput);
        return findMatcher.find();
    }

    /**
     * Returns corresponding command with the parsed parameters.
     * @param userInput String user input.
     * @return parsed command.
     * @throws NoCommandException If the user input can not be recognized as any command.
     * @throws IllegalDateTimeFormatException If the date time string is in invalid format.
     */
    public Command parseCommand(String userInput) throws
            NoCommandException, IllegalDateTimeFormatException {
        if (Parser.isExitKey(userInput)) {
            return new ExitCommand();
        } else if (Parser.isViewListKey(userInput)) {
            return new ViewListCommand();
        } else if (Parser.isDeleteKey(userInput)) {
            int deletedTaskIndex = this.findIndex(DELETE_PATTERN, userInput);
            return new DeleteCommand(deletedTaskIndex);
        } else if (Parser.isFinishKey(userInput)) {
            int finishedTaskIndex = this.findIndex(FINISH_PATTERN, userInput);
            return new FinishCommand(finishedTaskIndex);
        } else if (Parser.isTodoKey(userInput)) {
            String description = this.findDescription(TODO_PATTERN, userInput);
            return new AddToDoCommand(description);
        } else if (Parser.isDeadLineKey(userInput)) {
            String description = this.findDescription(DEADLINE_PATTERN, userInput);
            LocalDateTime by = this.findDateTime(DEADLINE_PATTERN, userInput);
            return new AddDeadlineCommand(description, by);
        } else if (Parser.isEventKey(userInput)) {
            String description = this.findDescription(EVENT_PATTERN, userInput);
            LocalDateTime at = this.findDateTime(EVENT_PATTERN, userInput);
            return new AddEventCommand(description, at);
        } else if (Parser.isFindKey(userInput)) {
            String keyWord = this.findKeyword(FIND_PATTERN, userInput);
            return new FindCommand(keyWord);
        }
        else {
            throw new NoCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }



}
