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
     *
     * @param command The user command.
     * @return The type of the command.
     */
    public static String getCommandType(String command) {
        // Split the string based on first space in command, first part is command type
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
    public static int getMarkDoneNum(String command) throws DukeUnknownInputException {
        // Check if command is longer than "Done "
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("Task number missing.");
        }
        String num = command.substring(5);
        assert num != null;
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
    public static String getTodoDescription(String command) throws DukeMissingDescriptionException {
        // Check if command is longer than "Todo "
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
    public static String[] getDeadlineParams(String command)
            throws DukeMissingDescriptionException, DukeUnknownInputException {
        // Check if command is longer than "Deadline "
        if (command.length() <= 9) {
            throw new DukeMissingDescriptionException("Deadline description and time missing.");
        }
        // Split the command with the first /by. The 2nd part should be when the deadline is
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
    public static String[] getEventParams(String command)
            throws DukeMissingDescriptionException, DukeUnknownInputException {
        // Check if command is longer than "Event "
        if (command.length() <= 6) {
            throw new DukeMissingDescriptionException("Event description and time missing.");
        }
        // Split the command with the first /by. The 2nd part should be the when the event is
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
    public static int getDeleteNum(String command)
            throws DukeMissingDescriptionException, DukeUnknownInputException {
        // Check if command is longer than "Delete "
        if (command.length() <= 7) {
            throw new DukeMissingDescriptionException("Task number missing.");
        }
        String num = command.substring(7);
        assert num != null;
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
    public static String getFindWord(String command) throws DukeMissingDescriptionException {
        // Check if command is longer than "Find "
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
     * @return Output message to the user.
     * @throws DukeUnknownInputException When the command type is not recognized.
     */
    public static String executeCommand(TaskList tasks, String command, Ui ui) throws DukeUnknownInputException {
        String commandType = getCommandType(command);
        switch (commandType) {
        case "list":
            return ui.showList(tasks);
        case "todo":
            String description = getTodoDescription(command);
            return ui.showAdded(tasks.addTodo(description), tasks.getLength());
        case "deadline":
            // descByWhen is size 2 array, first has description and second has deadline
            String[] descByWhen = getDeadlineParams(command);
            return ui.showAdded(tasks.addDeadline(descByWhen[0], descByWhen[1]), tasks.getLength());
        case "event":
            // descAtWhen is size 2 array, first has description and second has event time
            String[] descAtWhen = getEventParams(command);
            return ui.showAdded(tasks.addEvent(descAtWhen[0], descAtWhen[1]), tasks.getLength());
        case "done":
            int doneNum = getMarkDoneNum(command);
            return ui.showMarkedDone(tasks.markDone(doneNum));
        case "delete":
            int deleteNum = getDeleteNum(command);
            return ui.showDeleted(tasks.delete(deleteNum), tasks.getLength());
        case "find":
            String findWord = getFindWord(command);
            return ui.showFound(tasks.find(findWord));
        default:
            throw new DukeUnknownInputException("Sorry but I do not recognise your command.");
        }
    }
}