import java.text.ParseException;
import java.util.Scanner;

/**
 * Parser class that helps to make sense of the user commands.
 * A parser contains a scanner that helps it read commands from the users,
 * and understand the command that the user has input.
 */
public class Parser {

    protected Scanner sc;

    /**
     * Constructor for Parser class.
     * @param sc Scanner to create an instance of the Parser class.
     */
    public Parser(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Read the next command that the user provides.
     * @return The next command read.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Parse the command received to see what command type it belongs to.
     * @param command String which is read from the user input.
     * @return The key command word that determines the command type.
     * @throws DukeException If the string does not contain any recognised command types.
     */
    public String getCommandType(String command) throws DukeException {
        assert command != null : "Command cannot be null";
        if (command.contains("list")) {
            return "list";
        } else if (command.contains("find")) {
            return "find";
        } else if (command.contains("bye")) {
            return "bye";
        } else if (command.contains("done")) {
            return "done";
        } else if (command.contains("delete")) {
            return "delete";
        } else if (command.contains("deadline")) {
            return "deadline";
        } else if (command.contains("event")) {
            return "event";
        } else if (command.contains("todo")) {
            return "todo";
        } else if (command.contains("snooze")) {
            return "snooze";
        } else {
            throw new DukeException("Invalid command. Please try again.");
        }
    }

    /**
     * From a deadline command, return the Deadline task which would be created.
     * @param str Deadline command provided by the user.
     * @return Deadline task that is created from the command.
     * @throws DukeException If the deadline command does not specify the due date.
     * @throws ParseException If the deadline date cannot be processed.
     */
    public Deadline getDeadline(String str) throws DukeException, ParseException {
        assert str != null : "String cannot be null";
        String[] splitStr = str.split("/by ");

        if (splitStr.length < 2) {
            throw (new DukeException("The deadline requires a date it is due by."));
        }

        String description = splitStr[0];
        if (description.equals("deadline ")) {
            throw (new DukeException("The deadline requires a description."));
        }
        String timing = splitStr[1];
        String[] splitCommand = description.split(" ");

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < splitCommand.length; i++) {
            builder.append(splitCommand[i]);
            builder.append(" ");
            description = builder.toString();
        }
        return new Deadline(description, timing);

    }

    /**
     * From an Event command, return the Event task which would be created.
     * @param str Event command provided by the user.
     * @return Event task that is created from the command.
     * @throws DukeException If the Event command does not specify the due date.
     * @throws ParseException If the Event date cannot be processed.
     */
    public Event getEvent(String str) throws DukeException {
        assert str != null : "String cannot be null";
        String[] splitStr = str.split("/at ");

        if (splitStr.length < 2) {
            throw (new DukeException("The event requires a specific date."));
        }

        String description = splitStr[0];
        if (description.equals("event ")) {
            throw (new DukeException("The event requires a description."));
        }
        String timing = splitStr[1];
        String[] splitCommand = description.split(" ");

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < splitCommand.length; i++) {
            builder.append(splitCommand[i]);
            builder.append(" ");
            description = builder.toString();
        }
        return new Event(description, timing);
    }

    /**
     * From a ToDo command, return the ToDo task which would be created.
     * @param str ToDo command
     * @return ToDo task that is created from the command.
     * @throws DukeException If the ToDo command does not provide a description.
     */
    public ToDo getToDo(String str) throws DukeException {
        assert str != null : "String cannot be null";
        String[] splitStr = str.split("todo ");

        if (splitStr.length == 1) {
            throw (new DukeException("The To-Do requires a description."));
        }

        String description = splitStr[1];
        return new ToDo(description);
    }

    /**
     * Method to return the task number, based on array (zero-based) indexing.
     * @param command User provided command.
     * @return Index of task number based on zero-based indexing.
     */
    public int getTaskNo(String command) throws DukeException {
        assert command != null : "Command cannot be null";
        String[] str = command.split(" ");
        if (str.length < 2) {
            throw new DukeException("The command is too short. Please tell me which task you are referring to.");
        }
        return (Integer.parseInt(str[1]) - 1);
    }

    /**
     * Method to return the number of days to snooze the task.
     * @param command User provided command.
     * @return Integer number of days to snooze the task.
     */
    public int getSnoozeNo(String command) throws DukeException {
        assert command != null : "Command cannot be null";
        String[] str = command.split(" ");
        if (str.length < 3) {
            throw new DukeException("The command is too short. Please provide how many days to snooze by.");
        }
        return (Integer.parseInt(str[2]));
    }

    /**
     * Method to return the String to find.
     * @param command The entire find command given by the user.
     * @return String which user wants to find all matching tasks.
     */
    public String getFind(String command) {
        assert command != null : "Command cannot be null";
        String[] str = command.split(" ");
        return str[1];
    }
}
