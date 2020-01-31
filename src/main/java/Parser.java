import java.util.ArrayList;
import java.util.List;

public class Parser {

    // method to make sense of command and then act on that command

    /**
     * Function that makes sense of the command.
     * Also carries out the necessary actions for each command in the database.
     * @param command command inputted by user.
     * @param ui the UI class that handles all the aesthetics of the chatbot.
     * @param list Tasklist class that keeps track of tasks in the list.
     * @return boolean True when the user has not "closed" the chatbot else False.
     */
    public Command respondToUser(String command, UI ui, TaskList list) {
        // split the string
        String[] inputCommand = command.trim().split(" ");
        switch (inputCommand[0]) {
        case "todo":
            return new AddToDoCommand(command, false);
        case "bye":
            return new ByeCommand(command, true);
        case "event":
            return new AddEventCommand(command, false, this);
        case "deadline":
            return new AddDeadlineCommand(command, false, this);
        case "done":
            return new DoneCommand(command, false);
        case "delete":
            return new DeleteCommand(command, false);
        case "list":
            return new ListCommand(command, false);
        case "find":
            return new FindCommand(command, false);
        default:
            return new InvalidCommand(command, false);
        }
    }

    /**
     * Function to split the input query and grab the task name, also returns the index where the at/by will be at.
     * @param taskName name of the task.
     * @param inputCommand command inputted by user (array).
     * @param delimiter delimiter which is either /by or /at.
     * @return index for the by/at depending on the type of task, not applicable for to-do tasks.
     */
    public int grabTaskName(StringBuilder taskName, String[] inputCommand, String delimiter)  throws DukeException {
        int indexFound = 0; //find the index for the delimiter
        for (int i = 1; i <= inputCommand.length - 1; i++) {
            if (inputCommand[i].equals(delimiter)) {
                indexFound = i;
                break;
            } else {
                taskName.append(inputCommand[i]);
                if (inputCommand[i + 1].equals(delimiter)) {
                    indexFound = i + 1;
                    break;
                } else {
                    taskName.append(" ");
                }
            }
        }
        // if the inputCommand array index 1 == delimiter, means no description was given, throw exception
        if (indexFound == 1) {
            throw new DukeException("Description of deadline/event cannot be empty!");
        }
        return indexFound;
    }

    /**
     * Function to grab and get the date time for the event/deadline.
     * @param indexFound index where the /at or /by is found.
     * @param inputCommand command inputted by user (array).
     * @param dateTime object to hold the result.
     */
    public void grabDateTime(int indexFound, String[] inputCommand, StringBuilder dateTime)  throws DukeException {
        if (indexFound == inputCommand.length - 1) {
            // means that there is no description of date of task after the delimiter
            throw new DukeException("Date and time of the event/deadline cannot be empty!");
        }
        for (int i = indexFound + 1; i < inputCommand.length; i++) {
            dateTime.append(inputCommand[i]);
            if (i != inputCommand.length - 1) {
                dateTime.append(" ");
            }
        }
    }

}
