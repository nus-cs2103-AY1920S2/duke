package duke;

import duke.command.Add;
import duke.command.Command;
import duke.command.CommandList;
import duke.command.Delete;
import duke.command.Done;
import duke.command.Exit;
import duke.command.Help;
import duke.command.List;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represent how the Chatbot is able to understand user's command.
 *
 * @author Kenny Ho
 */
public class Parser {

    /**
     * Date format of user input.
     */
    public final static DateTimeFormatter USER_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Return a duke.command.Command object with respect to user input.
     * If user input consist of keyword todo, event or deadline
     * a duke.command.Add object will be returned.
     * If user input consist of keyword delete, a duke.command.Delete object
     * will be returned.
     * If user input consist of keyword done, a duke.command.Done object will be returned.
     * If user input consist of keyword list, a duke.command.List object will be returned.
     * If user input consist of keyword bye, a duke.command.Exit object will be returned.
     * If user input consist of keyword help, a duke.command.Help object will be returned.
     * else a duke.DukeException will be thrown with invalid command tagged to it.
     *
     * @param fullCommand String object of input given by user.
     * @return duke.command.Command object that represents the command of the user input.
     * @throws DukeException             if user input is empty, invalid or duke.command.Command description is empty.
     * @throws DateTimeException         if Date format is not in yyyy-mm-dd.
     * @throws IndexOutOfBoundsException if index accessing is more than amount of duke.task.Task exist or negative index.
     */
    public static Command parse(String fullCommand) throws DukeException, DateTimeException, IndexOutOfBoundsException {
        String[] inputArr = fullCommand.split(" ");
        String command = inputArr[0];
        if (fullCommand.length() == 0) {
            throw new DukeException("Type something", DukeErrorType.EMPTY_COMMAND);
        }
        CommandList commandValue;
        try {
            commandValue = CommandList.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new DukeException("Invalid command", DukeErrorType.INVALID_COMMAND);
        }
        switch (commandValue) {
        case TODO:
            return todoParser(fullCommand);
        case EVENT:
            return eventParser(fullCommand);
        case DEADLINE:
            return deadlineParser(fullCommand);
        case DELETE:
            return deleteParser(Integer.parseInt(inputArr[1]));
        case DONE:
            return doneParser(Integer.parseInt(inputArr[1]));
        case LIST:
            return listParser();
        case BYE:
            return byeParser();
        case HELP:
            return helpParser(fullCommand);
        default:
            throw new DukeException("Invalid command", DukeErrorType.INVALID_COMMAND);
        }
    }

    public static Help helpParser(String command) {
        String[] helpArr = command.split("help");
        if (helpArr.length == 0) {
            return new Help(Optional.empty());
        } else {
            return new Help(Optional.of(helpArr[1].trim()));
        }
    }
  
    public static Command todoParser(String input) throws DukeException {
        String[] todoArr = input.split("todo");
        if (todoArr.length == 0 || todoArr[1].trim().length() == 0) {
            throw new DukeException("Empty duke.task.ToDo description", DukeErrorType.EMPTY_DESCRIPTION, "todo");
        } else {
            String todoDescription = todoArr[1].trim();
            return new Add(new ToDo(todoDescription));
        }
    }

    public static Command eventParser(String input) throws DukeException, DateTimeException {
        String[] eventDetails = input.split("/at");
        if (eventDetails.length <= 1) {
            throw new DukeException("Empty duke.task.Event time", DukeErrorType.EMPTY_TIME, "event");
        }
        String eventTime = eventDetails[1].trim();
        String[] descriptionArr = eventDetails[0].split("event");
        String eventDescription = descriptionArr[1].trim();
        if (descriptionArr.length == 0 || eventDescription.length() == 0) {
            throw new DukeException("Empty duke.task.Event description", DukeErrorType.EMPTY_DESCRIPTION, "event");
        } else {
            return new Add(
                    new Event(eventDescription, LocalDate.parse(eventTime, USER_FORMAT)));
        }
    }

    public static Command deadlineParser(String input) throws DukeException, DateTimeException {
        String[] deadlineDetails = input.split("/by");
        if (deadlineDetails.length <= 1) {
            throw new DukeException("Empty duke.task.Deadline time", DukeErrorType.EMPTY_TIME, "deadline");
        }
        String deadlineTime = deadlineDetails[1].trim();
        String deadlineDescription = "";
        String[] descriptionArrDeadLine = deadlineDetails[0].split("deadline");
        deadlineDescription = descriptionArrDeadLine[1].trim();
        if (descriptionArrDeadLine.length == 0 || deadlineDescription.length() == 0) {
            throw new DukeException("Empty duke.task.Deadline description",
                    DukeErrorType.EMPTY_DESCRIPTION,
                    "deadline");
        } else {
            return new Add(
                new Deadline(deadlineDescription, LocalDate.parse(deadlineTime, USER_FORMAT)));
        }
    }

    public static Command deleteParser(int position) throws IndexOutOfBoundsException {
        int taskNumber = position - 1;
        return new Delete(taskNumber);
    }

    public static Command doneParser(int position) throws IndexOutOfBoundsException {
        int taskNumber = position - 1;
        return new Done(taskNumber);
    }

    public static Command listParser() {
        return new List();
    }

    public static Command byeParser() {
        return new Exit();
    }
}

