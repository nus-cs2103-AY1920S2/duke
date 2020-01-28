import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public final static DateTimeFormatter USER_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                }
                return new Add(new Event(eventDescription, LocalDate.parse(eventTime, USER_FORMAT)));
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

