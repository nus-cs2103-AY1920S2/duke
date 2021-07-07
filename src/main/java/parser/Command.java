package parser;

import exceptions.IllegalDateTimeFormatException;
import exceptions.IllegalPositionException;
import exceptions.NoDescriptionException;
import model.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static parser.Parser.parseDate;
import static parser.Parser.parseDateTime;

/**
 * Abstract class implementing part of the command object.
 */
public abstract class Command {
    protected TaskList taskList;

    private static final int POSITION_TARGET_INDEX = 2;

    /*Handle the difference that java list start from index 0 will human readable list
  starts from 1.*/
    private static final int DIFFERENCE_IN_START_INDEX = 1;
    private static final int POSITION_DESCRIPTION = 2;
    private static final int POSITION_DATE = 3;
    private static final int POSITION_TIME = 4;
    private static final int POSITION_VIEW_SCHEDULE_DATE = 2;
    private static final int POSITION_KEYWORD = 2;


    public Command() {

    }

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse the indicated position from the input command.
     * @param pattern Java regular expression pattern.
     * @param input String input.
     * @return parsed position as integer.
     */
    public int findIndex(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();

        String index = matcher.group(POSITION_TARGET_INDEX);
        assert index != null : "cannot find index";
        return Integer.parseInt(index) - DIFFERENCE_IN_START_INDEX;
    }

    /**
     * Parse the description from the input command.
     * @param pattern Java regular expression pattern.
     * @param input String input.
     * @return parsed description as string.
     */
    public String findDescription(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();

        String description = matcher.group(POSITION_DESCRIPTION);
        assert description != null : "cannot find index";
        return description.trim();
    }

    /**
     * Parse the date time from the command to LocalDateTime object.
     * @param pattern Java regular expression pattern.
     * @param input String user input.
     * @return LocalDateTime object.
     * @throws IllegalDateTimeFormatException If the string in invalid format.
     */
    public LocalDateTime findDateTime(Pattern pattern, String input) throws IllegalDateTimeFormatException {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String dateString = matcher.group(POSITION_DATE);
        String timeString = matcher.group(POSITION_TIME);

        assert dateString != null : "cannot find date string";
        return parseDateTime(dateString, timeString);
    }

    /**
     * Finds the date string from the user input by regex.
     * Converts the date string to LocalDate object.
     * @param pattern Regex used to parse user input.
     * @param input user input.
     * @return parse LocalDate object.
     * @throws IllegalDateTimeFormatException if the date string is in invalid format.
     */
    public LocalDate findDate(Pattern pattern, String input) throws IllegalDateTimeFormatException {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String dateString = matcher.group(POSITION_VIEW_SCHEDULE_DATE);

        assert dateString != null : "cannot find date string";
        return parseDate(dateString);
    }

    /**
     * Parse the target keyword from a command.
     * @param pattern the pattern to use.
     * @param input user input.
     * @return string of the keyword.
     */
    public String findKeyword(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();

        String keyWord = matcher.group(POSITION_KEYWORD);
        assert keyWord != null : "cannot find keyword";
        return matcher.group(POSITION_KEYWORD);
    }

    public abstract String execute() throws NoDescriptionException, IllegalPositionException;
}
