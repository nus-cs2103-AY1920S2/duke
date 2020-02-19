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
            throw new DukeMissingDescriptionException("Sorry I need the task number.\n"
                    + "e.g. done 2");
        }
        String num = command.substring(5);
        assert num != null;
        try {
            return Integer.valueOf(num);
        } catch (NumberFormatException e) {
            throw new DukeUnknownInputException("Sorry I need a task NUMBER.\n"
                    + "e.g. done 2");
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
            throw new DukeMissingDescriptionException("Sorry I need the Todo description.\ne.g. todo eat");
        }
        return command.substring(5);
    }

    /**
     * Gets the description and deadline of the Deadline task.
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
            throw new DukeMissingDescriptionException("Sorry I need the Deadline description and time.\n"
                    + "e.g. deadline eat /by tonight\n"
                    + "e.g. deadline drink /by 2019-12-02");
        }
        // Split the command with the first /by
        // Index 0 is description, index 1 is when the deadline is
        String[] splitted = command.substring(9).split(" /by ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Sorry please format as such:\n"
                    + "deadline (description) /by (time)\n"
                    + "e.g. deadline eat /by tonight\n"
                    + "e.g. deadline drink /by 2019-12-02");
        }
        // Check if date is parsable
        String byWhen = "";
        try {
            LocalDate date = LocalDate.parse(splitted[1]);
            //Format date e.g. from 2019-12-02 to Dec 2 2019
            byWhen = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            byWhen = splitted[1];
        } finally {
            String[] descriptionAndBy = {splitted[0], byWhen};
            return descriptionAndBy;
        }
    }

    /**
     * Gets the description and time of the Event task.
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
            throw new DukeMissingDescriptionException("Sorry I need Event description and time.\n"
                    + "e.g. event eat /at Monday 6pm\n"
                    + "e.g. event meet oppa /at 2019-12-02");
        }
        // Split the command with the first /at
        // Index 0 is description, index 1 is when the event is
        String[] splitted = command.substring(6).split(" /at ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Sorry please format as such:\n"
                    + "event (description) /at (time)\n"
                    + "e.g. event eat /at Monday 6pm\n"
                    + "e.g. event meet oppa /at 2019-12-02");
        }
        // Check if date is parsable
        String atWhen = "";
        try {
            LocalDate date = LocalDate.parse(splitted[1]);
            //Format date e.g. from 2019-12-02 to Dec 2 2019
            atWhen = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            atWhen = splitted[1];
        } finally {
            String[] descriptionAndAt = {splitted[0], atWhen};
            return descriptionAndAt;
        }
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
            throw new DukeMissingDescriptionException("Sorry I need task number.\n"
                    + "e.g. delete 2");
        }
        String num = command.substring(7);
        assert num != null;
        try {
            return Integer.valueOf(num);
        } catch (NumberFormatException e) {
            throw new DukeUnknownInputException("Sorry I need a task NUMBER.\n"
                    + "e.g. delete 2");
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
            throw new DukeMissingDescriptionException("Sorry I need what you're trying to find.\n"
                    + "e.g. find eat");
        }
        return command.substring(5);
    }

    /**
     * Gets the params needed to update task aka task number, T or D, info to change to.
     *
     * @param command User command.
     * @return Array where first is task number, second is T or D, third is info.
     */
    public static String[] getUpdateParams(String command)
            throws DukeMissingDescriptionException, DukeUnknownInputException {
        // Check if command is longer than e.g. "update 2 D "
        if (command.length() <= 11) {
            throw new DukeMissingDescriptionException("Sorry your Update command is incomplete.\n"
                    + "e.g. update 2 T tomorrow afternoon");
        }
        // Splits the command by first 2 spaces
        // Index 0 is task number, index 1 is D or T, index 2 is info to change
        String[] splitted = command.substring(7).split(" ", 3);
        if (splitted.length < 3) {
            throw new DukeUnknownInputException("Sorry please format as such:\n"
                    + "update (task number) (D or T depending on description or time) (change)\n"
                    + "e.g. update 2 T tomorrow afternoon");
        } else if (!splitted[0].matches("-?\\d+")) {
            throw new DukeUnknownInputException("Sorry I need a task number after update.\n"
                    + "e.g. update 2 T tomorrow afternoon");
        } else if (!splitted[1].equals("D") && !splitted[1].equals("T")) {
            throw new DukeUnknownInputException("Sorry I need either D or T after task number"
                    + "depending on if you are changing description or time\n"
                    + "e.g. update 2 T tomorrow afternoon\n"
                    + "e.g. update 3 D sleep");
        } else {
            return splitted;
        }
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
        case "help":
            return ui.showHello();
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
        case "update":
            String[] updateParams = getUpdateParams(command);
            return ui.showUpdated(tasks.update(Integer.valueOf(updateParams[0]), updateParams[1], updateParams[2]));
        default:
            throw new DukeUnknownInputException("Sorry but I do not recognise your command.");
        }
    }
}