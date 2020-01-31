import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * Return a Command object with respect to user input.
     * If user input consist of keyword todo, event or deadline
     * a Add object will be returned.
     * If user input consist of keyword delete, a Delete object
     * will be returned.
     * If user input consist of keyword done, a Done object will be returned.
     * If user input consist of keyword list, a List object will be returned.
     * IF user input consist of keyword bye, a Exit object will be returned.
     * else a DukeException will be thrown with invalid command tagged to it.
     *
     * @param fullCommand String object of input given by user.
     * @return Command object that represents the command of the user input.
     * @throws DukeException             if user input is empty, invalid or Command description is empty.
     * @throws DateTimeException         if Date format is not in yyyy-mm-dd.
     * @throws IndexOutOfBoundsException if index accessing is more than amount of Task exist or negative index.
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
            String[] todoArr = fullCommand.split("todo");
            if (todoArr.length == 0 || todoArr[1].trim().length() == 0) {
                throw new DukeException("Empty ToDo description", DukeErrorType.EMPTY_DESCRIPTION, command);
            } else {
                String todoDescription = todoArr[1].trim();
                return new Add(new ToDo(todoDescription));
            }
        case EVENT:
            String[] eventDetails = fullCommand.split("/at");
            String eventTime = eventDetails[1].trim();
            String[] descriptionArr = eventDetails[0].split("event");
            String eventDescription = descriptionArr[1].trim();
            if (eventDetails.length == 0) {
                throw new DukeException("Empty Event time", DukeErrorType.EMPTY_TIME, command);
            } else if (descriptionArr.length == 0 || eventDescription.length() == 0) {
                throw new DukeException("Empty Event description", DukeErrorType.EMPTY_DESCRIPTION, command);
            } else {
                return new Add(new Event(eventDescription, LocalDate.parse(eventTime, USER_FORMAT)));
            }
        case DEADLINE:
            String[] deadlineDetails = fullCommand.split("/by");
            String deadlineTime = deadlineDetails[1].trim();
            String deadlineDescription = "";
            String[] descriptionArrDeadLine = deadlineDetails[0].split("deadline");
            deadlineDescription = descriptionArrDeadLine[1].trim();
            return new Add(new Deadline(deadlineDescription, LocalDate.parse(deadlineTime, USER_FORMAT)));
        case DELETE:
            int deleteTaskNumber = Integer.parseInt(inputArr[1]) - 1;
            return new Delete(deleteTaskNumber);
        case DONE:
            int taskNumber = Integer.parseInt(inputArr[1]) - 1;
            return new Done(taskNumber);
        case LIST:
            return new List();
        case BYE:
            return new Exit();
        default:
            throw new DukeException("Invalid command", DukeErrorType.INVALID_COMMAND);
        }
    }
}

