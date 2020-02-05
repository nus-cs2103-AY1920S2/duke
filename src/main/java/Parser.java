import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import dukeexception.DukeMissingDescriptionException;
import dukeexception.DukeUnknownInputException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Gets the type of command aka what the user wants like add or delete tasks.
     * @param command The user command.
     * @return The type of the command.
     */
    public static String getCommandType(String command) {
        String[] splitted = command.split(" ", 2);
        return splitted[0];
    }

    /**
     * Gets the task number from the user command to be marked done.
     *
     * @param command User command.
     * @return The task number to be marked done.
     * @throws DukeMissingDescriptionException When there is no task number given.
     * @thows DukeUnknownInputException When what user gives is not a number.
     */
    public static int markDoneNum(String command) {
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("Task number missing.");
        }
        String num = command.substring(5);
        try {
            return Integer.valueOf(num);
        } catch (NumberFormatException e) {
            throw new DukeUnknownInputException("Need task NUMBER.");
        }
    }

    /**
     * Gets the description for the To Do task.
     *
     * @param command User command.
     * @return The description of the To Do task.
     * @throws DukeMissingDescriptionException If there is no description given.
     */
    public static String todoDescription(String command) {
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("Todo description missing.");
        }
        return command.substring(5);
    }

    /**
     * Gives the description and deadline of the Deadline task.
     *
     * @param command User command.
     * @return A String[] where 0 index contains the description and 1 index contains the deadline.
     * @throws DukeMissingDescriptionException When no description of deadline is given by user.
     * @throws DukeUnknownInputException When /by is missing in command.
     */
    public static String[] deadlineParams(String command) {
        if (command.length() <= 9) {
            throw new DukeMissingDescriptionException("Deadline description and time missing.");
        }
        String[] splitted = command.substring(9).split(" /by ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Need give/format deadline <description> /by <time>.");
        }
        // Check if date is parsable
        String byWhen = "";
        try {
            LocalDate date = LocalDate.parse(splitted[1]);
            byWhen = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            byWhen = splitted[1];
        } finally {
            String[] descriptionAndBy = {splitted[0], byWhen};
            return descriptionAndBy;
        }
    }

    /**
     * Gives the description and time of the Event task.
     *
     * @param command User command.
     * @return A String[] where 0 index contains the description and 1 index contains the time of event.
     * @throws DukeMissingDescriptionException When no description of event is given by user.
     * @throws DukeUnknownInputException When /at is missing in command.
     */
    public static String[] eventParams(String command) {
        if (command.length() <= 6) {
            throw new DukeMissingDescriptionException("Event description and time missing.");
        }
        String[] splitted = command.substring(6).split(" /at ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Need format event <description> /at <time>.");
        }
        return splitted;
    }

    /**
     * Gets the task number of the task to be deleted from user command.
     *
     * @param command User Command.
     * @return An integer that is the task number to be deleted.
     * @throws DukeMissingDescriptionException When no number is given.
     * @throws DukeUnknownInputException When what user give is not a number.
     */
    public static int deleteNum(String command) {
        if (command.length() <= 7) {
            throw new DukeMissingDescriptionException("Task number missing.");
        }
        String num = command.substring(7);
        try {
            return Integer.valueOf(num);
        } catch (NumberFormatException e) {
            throw new DukeUnknownInputException("Need task NUMBER.");
        }
    }

    /**
     * Gets the word to find from user command.
     *
     * @param command User Command.
     * @return Word to find in String format.
     * @throws DukeMissingDescriptionException When nothing is given to find by user.
     */
    public static String findWord(String command) {
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("Word to find missing.");
        }
        return command.substring(5);
    }

    /**
     * Executes the command given by the user.
     *
     * @param tasks TaskList object to be edited based on the command.
     * @param command User Command.
     * @param ui Ui to handle the output depending on command type.
     * @throws DukeUnknownInputException When the command type is not recognized.
     */
    public static void executeCommand(TaskList tasks, String command, Ui ui)  throws DukeUnknownInputException {
        String commandType = getCommandType(command);
        switch (commandType) {
        case "list":
            ui.showList(tasks);
            break;
        case "todo":
            String description = todoDescription(command);
            ui.showAdded(tasks.addTodo(description), tasks.getLength());
            break;
        case "deadline":
            String[] descByWhen = deadlineParams(command);
            ui.showAdded(tasks.addDeadline(descByWhen[0], descByWhen[1]), tasks.getLength());
            break;
        case "event":
            String[] descAtWhen = eventParams(command);
            ui.showAdded(tasks.addEvent(descAtWhen[0], descAtWhen[1]), tasks.getLength());
            break;
        case "done":
            int doneNum = markDoneNum(command);
            ui.showMarkedDone(tasks.markDone(doneNum));
            break;
        case "delete":
            int deleteNum = deleteNum(command);
            ui.showDeleted(tasks.delete(deleteNum), tasks.getLength());
            break;
        case "find":
            String findWord = findWord(command);
            ui.showFound(tasks.find(findWord));
            break;
        default:
            throw new DukeUnknownInputException("Sorry but I do not recognise your command.");
        }
    }
}