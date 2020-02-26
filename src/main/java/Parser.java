import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a object that makes sense of the user input into the program.
 */
public class Parser {

    /**
     * Returns a Command object that is generated from the user's input into the program.
     * The type of Command object that is returned depends on the user's input.
     * Throws a NullPointerException if the user's input is not of the correct format.
     * @param input the input String from the user into the program.
     * @return a Command object based on the user's input.
     * @throws NullPointerException thrown if the user input is not in the correct format.
     */
    public Command parse(String input) throws DukeException {
        assert (!input.isEmpty());
        String[] splitInput = input.split(" ");
        String cmd = splitInput[0];
        Command command = null;
        switch (cmd) {
        case "bye":
            command = new ByeCommand(cmd);
            break;
        case "list":
            command = new ListCommand(cmd);
            break;
        case "done":
            // Read the task number as the next element of splitInput
            int taskNumber = Integer.parseInt(splitInput[1]);
            command = new MarkDoneCommand(cmd, taskNumber);
            break;
        case "delete":
            int taskNumberDelete = Integer.parseInt(splitInput[1]);
            command = new DeleteCommand(cmd, taskNumberDelete);
            break;
        case "find":
            command = new FindCommand(input);
            break;
        case "todo":
        case "event":
        case "deadline":
            Command cmdToReturn;
            if (inputValidDateFormat(input)) {
                // check if the input has the correct date format
                cmdToReturn = new AddTaskCommand(cmd, input);
            } else {
               // else classify it as another, unrecognized command
                cmdToReturn = new OtherCommand(input);
            }
            command = cmdToReturn;
            break;
        case "trivia":
            command = new TriviaCommand(input);
            break;
        default:
            // If the user input is not valid, it will be classified as
            // other command, which will be flagged as an error
            command = new OtherCommand(input);
            break;
        }
        return command;
    }

    private boolean inputValidDateFormat(String input) {
        String[] arr;
        if (input.contains("/by")) {
            arr = input.split("/by");
        } else {
            arr = input.split("/at");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate date = LocalDate.parse(arr[1].trim(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
