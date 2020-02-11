package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ShowListCommand;
import duke.command.DoneCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;

import duke.command.SortCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser which handle the parsing of user input.
 *
 * @author Wang Yuting
 */
public class Parser {

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
     * @throws DukeException If the input is not valid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        final String OPTION_BYE = "bye";
        final String OPTION_LIST = "list";
        final String OPTION_DONE = "done";
        final String OPTION_TODO = "todo";
        final String OPTION_DEADLINE = "deadline";
        final String OPTION_EVENT = "event";
        final String OPTION_DELETE = "delete";
        final String OPTION_FIND = "find";
        final String OPTION_SORT = "sort";

        Command command = null;
        if (fullCommand.isBlank()) {
            throw new DukeException("randomInput");
        }

        String[] inputParts = fullCommand.split(" ", 2);
        String option = inputParts[0];
        String desc = inputParts.length == 2 ? inputParts[1] : "";

        switch (option.toLowerCase()) {
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

        default:
            throw new DukeException("randomInput");
        }
        return command;
    }

    /** Parses exit command */
    private static Command parseExitCommand(String desc) throws DukeException {
        if (!desc.isBlank()) {
            throw new DukeException("randomInput");
        }
        return new ExitCommand();
    }

    /** Parses list command */
    private static Command parseListCommand(String desc) throws DukeException {
        if (!desc.isBlank()) {
            throw new DukeException("randomInput");
        }
        return new ShowListCommand();
    }

    /** Parses done command */
    private static Command parseDoneCommand(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new DukeException("doneMissingIndex");
        }
        if (!isInteger(desc.trim())) {
            throw new DukeException("doneWrongIndexFormat");
        }
        int index = Integer.parseInt(desc);
        return new DoneCommand(index);
    }

    /** Parses todo command */
    private static Command parseAddTodoCommand(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new DukeException("taskMissingDescription");
        }
        ToDo newTodo = new ToDo(desc.trim());
        return new AddCommand(newTodo);
    }

    /** Parses deadline command */
    private static Command parseAddDeadlineCommand(String desc) throws DukeException {
        String[] tempInputParts;
        LocalDateTime inputTime = null;

        if (desc.isBlank()) {
            throw new DukeException("taskMissingDescription");
        }
        if (!desc.contains("/by")) {
            throw new DukeException("deadline&eventWrongDescriptionFormat");
        }

        tempInputParts = desc.trim().split("/by");
        if (tempInputParts.length != 2 || tempInputParts[0].isBlank()) {
            throw new DukeException("deadline&eventWrongDescriptionFormat");
        }
        try {
            inputTime = LocalDateTime.parse(tempInputParts[1].trim(), Task.DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTimeParseError");
        }

        if (inputTime.isBefore(LocalDateTime.now())) {
            throw new DukeException("pastDateTime");
        }
        Deadline newDeadline = new Deadline(tempInputParts[0].trim(), inputTime);
        return new AddCommand(newDeadline);
    }

    /** Parses event command */
    private static Command parseEventCommand(String desc) throws DukeException  {
        String[] tempInputParts;
        String[] tempInputDateTimes;
        LocalDateTime start = null;
        LocalDateTime end = null;

        // filter out the invalid input without description, or without "/at" or "-"
        if (desc.isBlank()) {
            throw new DukeException("taskMissingDescription");
        }
        if (!desc.contains("/at") || !desc.contains("-")) {
            throw new DukeException("deadline&eventWrongDescriptionFormat");
        }

        // filter out the invalid input which is not in "desc + /at + start-end" format
        tempInputParts = desc.trim().split("/at");
        if (tempInputParts.length != 2 || tempInputParts[0].isBlank() || !tempInputParts[1].contains("-")) {
            throw new DukeException("deadline&eventWrongDescriptionFormat");
        }

        // filter out the invalid input with wrong "start-end" format
        tempInputDateTimes = tempInputParts[1].split("-");
        if(tempInputDateTimes.length != 2 || tempInputDateTimes[0].isBlank()){
            throw new DukeException("DateTimeParseError");
        }

        try {
            start = LocalDateTime.parse(tempInputDateTimes[0].trim(), Task.DATETIME_FORMAT);
            end = LocalDateTime.parse(tempInputDateTimes[1].trim(), Task.DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTimeParseError");
        }

        // filter out the input with correct format but invalid date time
        if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
            throw new DukeException("pastDateTime");
        }
        if (end.isBefore(start)) {
            throw new DukeException("EventEndBeforeStartError");
        }

        Event newEvent = new Event(tempInputParts[0], start, end);
        return new AddCommand(newEvent);
    }

    /** Parses delete command */
    private static Command parseDeleteCommand(String desc) throws DukeException  {
        if (desc.isBlank()) {
            throw new DukeException("deleteMissingIndex");
        }
        if (!isInteger(desc.trim())) {
            throw new DukeException("deleteWrongIndexFormat");
        }
        return new DeleteCommand(Integer.parseInt(desc));
    }

    /** Parses find command */
    private static Command parseFindCommand(String desc) throws DukeException  {
        if (desc.isBlank()) {
            throw new DukeException("findMissingKeyword");
        }
        return new FindCommand(desc);
    }

    /** Parse sort command */
    private static Command parseSortCommand(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new DukeException("sortMissingSortTerm");
        }
        if(!desc.equals("deadline") && !desc.equals("description")){
            throw new DukeException("sortWrongSortTermFormat");
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
        return dateTime.getHour() + ":" + dateTime.getMinute()
                + " " + dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear();
    }

}
