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
    public static final String DATE_TIME_KEY = "(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})?";

    private static final String DEFAULT_TIME = "23:59";
    private static final String EXIT_KEY = "bye";
    private static final String VIEW_LIST_KEY = "list";
    private static final String DELETE_KEY = "(delete)\\s*(\\d+)";
    private static final String FINISH_KEY = "(done)\\s*(\\d+)";
    private static final String TODO_KEY = "(todo)(.*)";
    private static final String DEADLINE_KEY = "(deadline)(.*)/by\\s*" + DATE_TIME_KEY;
    private static final String EVENT_KEY = "(event)(.*)/at\\s*" + DATE_TIME_KEY;
    private static final String FIND_KEY = "(find)\\s*(\\S+)";
    private static final String VIEW_SCHEDULE_KEY = "(view schedule)\\s*" + DATE_TIME_KEY;

    static final Pattern DELETE_PATTERN = Pattern.compile(DELETE_KEY);
    static final Pattern FINISH_PATTERN = Pattern.compile(FINISH_KEY);
    static final Pattern TODO_PATTERN = Pattern.compile(TODO_KEY);
    static final Pattern DEADLINE_PATTERN = Pattern.compile(DEADLINE_KEY);
    static final Pattern EVENT_PATTERN = Pattern.compile(EVENT_KEY);
    static final Pattern FIND_PATTERN = Pattern.compile(FIND_KEY);
    static final Pattern VIEW_SCHEDULE_PATTERN = Pattern.compile(VIEW_SCHEDULE_KEY);

    /**
     * static method converts string to date time object.
     * @param dateString A valid string representing the date.
     * @param timeString A valid string representing the time.
     * @return LocalDateTime object.
     * @throws IllegalDateTimeFormatException if the string is invalid.
     */
    public static LocalDateTime parseDateTime(String dateString, String timeString) throws
            IllegalDateTimeFormatException {
        try {
            LocalDate date = LocalDate.parse(dateString);
            LocalTime time = LocalTime.parse(timeString == null ? DEFAULT_TIME : timeString);
            return date.atTime(time);
        } catch (DateTimeParseException dte) {
            throw new IllegalDateTimeFormatException(dte.getMessage() + '\n');
        } catch (NullPointerException npe) {
            throw new IllegalDateTimeFormatException("Oops!!! The date string is missing!\n");
        }
    }

    /**
     * Converts date string to LocalDate format.
     * @param dateString A valid string representing the date.
     * @return LocalDate object.
     * @throws IllegalDateTimeFormatException If the date string is not valid.
     */
    public static LocalDate parseDate(String dateString) throws
            IllegalDateTimeFormatException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException dte) {
            throw new IllegalDateTimeFormatException(dte.getMessage() + '\n');
        } catch (NullPointerException npe) {
            throw new IllegalDateTimeFormatException("Oops!!! The date string is missing!\n");
        }
    }

    public static boolean isExitKey(String input) {
        return EXIT_KEY.equals(input);
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

    private static boolean isFindKey(String input) {
        Matcher findMatcher = FIND_PATTERN.matcher(input);
        return findMatcher.find();
    }

    private static boolean isViewScheduleKey(String input) {
        Matcher viewScheduleMatcher = VIEW_SCHEDULE_PATTERN.matcher(input);
        return viewScheduleMatcher.find();
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
            return new DeleteCommand(userInput);
        } else if (Parser.isFinishKey(userInput)) {
            return new FinishCommand(userInput);
        } else if (Parser.isTodoKey(userInput)) {
            return new AddToDoCommand(userInput);
        } else if (Parser.isDeadLineKey(userInput)) {
            return new AddDeadlineCommand(userInput);
        } else if (Parser.isEventKey(userInput)) {
            return new AddEventCommand(userInput);
        } else if (Parser.isFindKey(userInput)) {
            return new FindCommand(userInput);
        } else if (Parser.isViewScheduleKey(userInput)) {
            return new ViewScheduleCommand(userInput);
        } else {
            throw new NoCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
