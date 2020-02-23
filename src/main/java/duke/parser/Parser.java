/**
 * Filters user input to determine type of command to be used
 */
package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.MissingDetailsException;
import duke.exceptions.UnrecognizedCommandException;
import duke.storage.Storage;
import duke.tasks.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String datePattern = "MMM d yyyy";
    public static String timePattern = "HH:mm";
    public static DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern(timePattern);
    public static DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern(datePattern);

    /**
     * Returns the command corresponding to user input
     * @param input Input received from user
     * @return Command requested by user
     */
    public static String parse(String input, TaskList taskList, Storage storage) throws DukeException {

        String[] inputArr = input.split(" ",2);
        String cmd = inputArr[0].toUpperCase();

        CommandType commandType;
        try {
            // Check if input command is in enum list
            commandType = CommandType.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            throw new UnrecognizedCommandException();
        }

        String commandDetails = "";
        // Only BYE and LIST commands do not have further details
        if (commandType != CommandType.BYE && commandType != CommandType.LIST && commandType != CommandType.HELP) {
            try {
                commandDetails = inputArr[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingDetailsException();
            }
        }

        switch (commandType) {
        case BYE:
            return ExitCommand.execute();
        case DEADLINE:
            return AddDeadlineCommand.execute(commandDetails, taskList, storage);
        case DELETE:
            return DeleteCommand.execute(commandDetails, taskList, storage);
        case DONE:
            return DoneCommand.execute(commandDetails, taskList, storage);
        case EVENT:
            return AddEventCommand.execute(commandDetails, taskList, storage);
        case FIND:
            return FindCommand.execute(commandDetails, taskList);
        case HELP:
            return HelpCommand.execute();
        case LIST:
            return ListCommand.execute(taskList);
        case SCHEDULE:
            return ScheduleCommand.execute(commandDetails, taskList);
        case TODO:
            return AddTodoCommand.execute(commandDetails, taskList, storage);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    /**
     * Checks If user input date is of the correct format
     * @param inputDate Date input from user
     * @return LocalDate object of format "yyyy-MM-dd" if input is of correct format
     * @throws DateTimeParseException If input format is wrong
     */
    public static LocalDate parseDate(String inputDate) throws DateTimeParseException {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(inputDate, inputDateFormatter);
        return date;
    }

    /**
     * Checks If user input time is of the correct format
     * @param inputTime Time input from user
     * @return LocalTime object of format "HHmm" if input is of correct format
     * @throws DateTimeParseException If input format is wrong
     */
    public static LocalTime parseTime(String inputTime) throws DateTimeParseException {
        String inputTimePattern = "HHmm";
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern(inputTimePattern);
        LocalTime time = LocalTime.parse(inputTime, inputTimeFormatter);
        return time;
    }
}
