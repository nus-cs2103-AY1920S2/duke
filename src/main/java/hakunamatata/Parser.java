package hakunamatata;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import hakunamatata.command.AddCommand;
import hakunamatata.command.Command;
import hakunamatata.command.DeleteCommand;
import hakunamatata.command.DoneCommand;
import hakunamatata.command.ExitCommand;
import hakunamatata.command.FindCommand;
import hakunamatata.command.HelpCommand;
import hakunamatata.command.ShowListCommand;
import hakunamatata.command.SortCommand;
import hakunamatata.command.SpecialCommand;
import hakunamatata.task.Deadline;
import hakunamatata.task.Event;
import hakunamatata.task.Task;
import hakunamatata.task.ToDo;

/**
 * Represents a parser which handle the parsing of user input.
 *
 * @author Wang Yuting
 */
public class Parser {
    static final String OPTION_BYE = "bye";
    static final String OPTION_LIST = "list";
    static final String OPTION_DONE = "done";
    static final String OPTION_TODO = "todo";
    static final String OPTION_DEADLINE = "deadline";
    static final String OPTION_EVENT = "event";
    static final String OPTION_DELETE = "delete";
    static final String OPTION_FIND = "find";
    static final String OPTION_SORT = "sort";
    static final String OPTION_HELP = "help";

    /**
     * Creates a <code>Parser</code>.
     */
    public Parser() {

    }

    /**
     * Parse a user input to get valid <code>Command</code>.
     *
     * @param fullCommand The raw user input read by <code>UI</code>.
     * @return A valid <code>Command</code>.
     * @throws HakunaMatataException If the input is not valid.
     */
    public static Command parse(String fullCommand) throws HakunaMatataException {

        Command command = null;
        if (fullCommand.isBlank()) {
            throw new HakunaMatataException("randomInput");
        } else {
            fullCommand = fullCommand.trim();
        }

        if (Arrays.asList(SpecialCommand.specialTerm).contains(fullCommand)) {
            command = parseSpecialCommand(fullCommand);
            return command;
        }

        String[] inputParts = fullCommand.split(" ", 2);
        String option = inputParts[0];
        String desc = inputParts.length == 2 ? inputParts[1] : "";

        switch (option.toLowerCase().trim()) {
        case OPTION_BYE:
            command = parseExitCommand(desc);
            break;

        case OPTION_LIST:
            command = parseListCommand(desc);
            break;

        case OPTION_DONE:
            command = parseDoneCommand(desc);
            break;

        case OPTION_TODO:
            command = parseAddTodoCommand(desc);
            break;

        case OPTION_DEADLINE:
            command = parseAddDeadlineCommand(desc);
            break;

        case OPTION_EVENT:
            command = parseEventCommand(desc);
            break;

        case OPTION_DELETE:
            command = parseDeleteCommand(desc);
            break;

        case OPTION_FIND:
            command = parseFindCommand(desc);
            break;

        case OPTION_SORT:
            command = parseSortCommand(desc);
            break;

        case OPTION_HELP:
            command = parseHelpCommand(desc);
            break;

        default:
            throw new HakunaMatataException("randomInput");
        }
        return command;
    }

    /**
     * Parses special command.
     */
    private static Command parseSpecialCommand(String specialTerm) throws HakunaMatataException {
        return new SpecialCommand(specialTerm);
    }

    /**
     * Parses help command.
     */
    private static Command parseHelpCommand(String desc) throws HakunaMatataException {
        if (!desc.isBlank()) {
            throw new HakunaMatataException("randomInput");
        }
        return new HelpCommand();
    }

    /**
     * Parses exit command.
     */
    private static Command parseExitCommand(String desc) throws HakunaMatataException {
        if (!desc.isBlank()) {
            throw new HakunaMatataException("randomInput");
        }
        return new ExitCommand();
    }

    /**
     * Parses list command.
     */
    private static Command parseListCommand(String desc) throws HakunaMatataException {
        if (!desc.isBlank()) {
            throw new HakunaMatataException("randomInput");
        }
        return new ShowListCommand();
    }

    /**
     * Parses done command.
     */
    private static Command parseDoneCommand(String desc) throws HakunaMatataException {
        if (desc.isBlank()) {
            throw new HakunaMatataException("doneMissingIndex");
        }
        if (!isInteger(desc.trim())) {
            throw new HakunaMatataException("doneWrongIndexFormat");
        }
        int index = Integer.parseInt(desc);
        return new DoneCommand(index);
    }

    /**
     * Parses todo command.
     */
    private static Command parseAddTodoCommand(String desc) throws HakunaMatataException {
        if (desc.isBlank()) {
            throw new HakunaMatataException("taskMissingDescription");
        }
        ToDo newTodo = new ToDo(desc.trim());
        return new AddCommand(newTodo);
    }

    /**
     * Parses deadline command.
     */
    private static Command parseAddDeadlineCommand(String desc) throws HakunaMatataException {
        String[] tempInputParts;
        LocalDateTime inputTime = null;

        if (desc.isBlank()) {
            throw new HakunaMatataException("taskMissingDescription");
        }
        if (!desc.contains("/by")) {
            throw new HakunaMatataException("deadline&eventWrongDescriptionFormat");
        }

        tempInputParts = desc.trim().split("/by");
        if (tempInputParts.length != 2 || tempInputParts[0].isBlank()) {
            throw new HakunaMatataException("deadline&eventWrongDescriptionFormat");
        }
        try {
            inputTime = LocalDateTime.parse(tempInputParts[1].trim(), Task.DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new HakunaMatataException("DateTimeParseError");
        }

        if (inputTime.isBefore(LocalDateTime.now())) {
            throw new HakunaMatataException("pastDateTime");
        }
        Deadline newDeadline = new Deadline(tempInputParts[0].trim(), inputTime);
        return new AddCommand(newDeadline);
    }

    /**
     * Parses event command.
     */
    private static Command parseEventCommand(String desc) throws HakunaMatataException {
        String[] tempInputParts;
        LocalDateTime start = null;
        LocalDateTime end = null;

        // filter out the invalid input without description, or without "/at" or "-"
        if (desc.isBlank()) {
            throw new HakunaMatataException("taskMissingDescription");
        }
        if (!desc.contains("/at") || !desc.contains("-")) {
            throw new HakunaMatataException("deadline&eventWrongDescriptionFormat");
        }

        // filter out the invalid input which is not in "desc + /at + start-end" format
        tempInputParts = desc.trim().split("/at");
        if (tempInputParts.length != 2 || tempInputParts[0].isBlank() || !tempInputParts[1].contains("-")) {
            throw new HakunaMatataException("deadline&eventWrongDescriptionFormat");
        }

        // filter out the invalid input with wrong "start-end" format
        String[] tempInputDateTimes;
        tempInputDateTimes = tempInputParts[1].split("-");
        if (tempInputDateTimes.length != 2 || tempInputDateTimes[0].isBlank()) {
            throw new HakunaMatataException("DateTimeParseError");
        }

        try {
            start = LocalDateTime.parse(tempInputDateTimes[0].trim(), Task.DATETIME_FORMAT);
            end = LocalDateTime.parse(tempInputDateTimes[1].trim(), Task.DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new HakunaMatataException("DateTimeParseError");
        }

        // filter out the input with correct format but invalid date time
        if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
            throw new HakunaMatataException("pastDateTime");
        }
        if (end.isBefore(start)) {
            throw new HakunaMatataException("EventEndBeforeStartError");
        }

        Event newEvent = new Event(tempInputParts[0], start, end);
        return new AddCommand(newEvent);
    }

    /**
     * Parses delete command.
     */
    private static Command parseDeleteCommand(String desc) throws HakunaMatataException {
        if (desc.isBlank()) {
            throw new HakunaMatataException("deleteMissingIndex");
        }
        if (!isInteger(desc.trim())) {
            throw new HakunaMatataException("deleteWrongIndexFormat");
        }
        return new DeleteCommand(Integer.parseInt(desc));
    }

    /**
     * Parses find command.
     */
    private static Command parseFindCommand(String desc) throws HakunaMatataException {
        if (desc.isBlank()) {
            throw new HakunaMatataException("findMissingKeyword");
        }
        return new FindCommand(desc);
    }

    /**
     * Parse sort command.
     */
    private static Command parseSortCommand(String desc) throws HakunaMatataException {
        if (desc.isBlank()) {
            throw new HakunaMatataException("sortMissingSortTerm");
        }
        if (!desc.equals("deadline") && !desc.equals("description")) {
            throw new HakunaMatataException("sortWrongSortTermFormat");
        }
        return new SortCommand(desc);
    }


    /**
     * Checks whether the input String is an integer.
     *
     * @param s The input String need to be checked.
     * @return True if the String is an integer, false if it is not.
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Converts the input <code>LocalDateTime</code> into a String.
     *
     * @return The date and time of the input <code>LocalDateTime</code> in String.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        String min = dateTime.getMinute() < 10 ? "0" + dateTime.getMinute()
                : "" + dateTime.getMinute();
        String hour = dateTime.getHour() < 10 ? "0" + dateTime.getHour()
                : "" + dateTime.getHour();
        String day = dateTime.getDayOfMonth() < 10 ? "0" + dateTime.getDayOfMonth()
                : "" + dateTime.getDayOfMonth();
        String month = dateTime.getMonthValue() < 10 ? "0" + dateTime.getMonthValue()
                : "" + dateTime.getMonthValue();
        return hour + ":" + min
                + " " + day + "/" + month + "/" + dateTime.getYear();
    }

}
