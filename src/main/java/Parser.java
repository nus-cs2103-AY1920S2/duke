import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

    private Commands command;
    private int indexOfTaskAffected = -1;
    private Task newTask;
    private String keyword;

    /**
     * Decodes user input and generates a Parser object that is easier to understand.
     * com saves the type of Command that the user input.
     * indexOfTaskAffected saves the index for commands that require indexes.
     * newTask saves a Task for commands that create a new task.
     * @param line String to be decoded.
     * @param size Current number of tasks.
     * @throws DukeException If there was an invalid user input.
     */
    public Parser(String line, int size) throws DukeException {
        switch (line) {
        case "bye":
            command = Commands.BYE;
            break;

        case "list":
            command = Commands.LIST_TASKS;
            break;

        default:
            String[] comArs = line.split("\\s", 2);
            String inputCommand = comArs[0];
            int index;
            String details;
            String[] msgAndDate;

            switch (inputCommand) {
            case "done":
                if (comArs.length == 1) {
                    throw new DukeExceptionIndex("done");
                }
                index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DukeExceptionIndex("done");
                }

                command = Commands.DONE;
                indexOfTaskAffected = index;
                break;

            case "delete":
                if (comArs.length == 1) {
                    throw new DukeExceptionIndex("delete");
                }
                index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DukeExceptionIndex("delete");
                }

                command = Commands.DEL_TASK;
                indexOfTaskAffected = index;
                break;

            case "todo":
                details = line.substring(4, line.length());
                if (details.isBlank()) {
                    throw new DukeExceptionDescription("todo");
                }
                command = Commands.NEW_TASK;
                newTask = new ToDo(details);
                break;

            case "event":
                details = line.substring(5, line.length());
                if (details.isBlank()) {
                    throw new DukeExceptionDescription("event");
                }
                msgAndDate = details.split(" /at ", 2);
                if (msgAndDate.length == 1) {
                    throw new DukeExceptionDate("event");
                }

                try {
                    LocalDate date = LocalDate.parse(msgAndDate[1]);
                    command = Commands.NEW_TASK;
                    newTask = new Event(msgAndDate[0], date);
                } catch (DateTimeParseException e) {
                    throw new DukeExceptionDateFormat();
                }
                break;

            case "deadline":
                details = line.substring(8, line.length());
                if (details.isBlank()) {
                    throw new DukeExceptionDescription("deadline");
                }
                msgAndDate = details.split(" /by ", 2);
                if (msgAndDate.length == 1) {
                    throw new DukeExceptionDate("deadline");
                }

                try {
                    LocalDate date = LocalDate.parse(msgAndDate[1]);
                    command = Commands.NEW_TASK;
                    newTask = new Deadline(msgAndDate[0], date);
                } catch (DateTimeParseException e) {
                    throw new DukeExceptionDateFormat();
                }
                break;

            case "find":
                command = Commands.FIND;
                keyword = line.substring(4, line.length());
                break;

            default:
                throw new DukeExceptionCommand();
            }
        }
    }

    public Task getTask() {
        return newTask;
    }

    public String getKeyWord() {
        return keyword;
    }

    public Commands getCommand() {
        return command;
    }

    public int getIndexAffected() {
        return indexOfTaskAffected;
    }
}
