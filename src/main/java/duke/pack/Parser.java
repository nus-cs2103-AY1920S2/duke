package duke.pack;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to interpret the user's commands.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Interprets the user's command.
     * @param command input of the user
     * @return a Command that indicates what the user wants
     * @throws DukeException if command is not correctly inputted
     */
    public static Command parseCommand(String command) throws DukeException {
        String[] comm = command.split(" ");

        if (command.equals("list")) {
            // list command to print all the tasks
            return new ListCommand();

        } else if (comm[0].equals("find")) {
            // find command that returns tasks that match
            return parseFind(comm);

        } else if (comm[0].equals("done")) {
            // done
            return parseDone(comm);

        } else if (comm[0].equals("delete")) {
            // delete
            return parseDelete(comm);

        } else if (comm[0].equals("todo")) {
            // to-do
            return parseTodo(command, comm);

        } else if (comm[0].equals("event")) {
            // event
            return parseEvent(command, comm);

        } else if (comm[0].equals("deadline")) {
            // deadline
            return parseDeadline(command, comm);

        } else if (command.equals("bye")) {
            return new ExitCommand();

        } else {
            // invalid command
            throw new DukeException("    Oh no! I'm sorry, I do not understand that, please try again!");
        }

    }

    public static Command parseFind(String[] comm) throws DukeException {
        if (comm.length == 1) {
            throw new DukeException("    Oh no! You have to specify what you want to find!");
        }

        Command c = new FindCommand(comm[1]);
        return c;
    }

    public static Command parseDone(String[] comm) throws DukeException {
        if (comm.length == 1) {
            throw new DukeException("    Oh no! You have to specify which task is done!");
        }

        Command c = new DoneCommand(Integer.parseInt(comm[1]));
        return c;
    }

    public static Command parseDelete(String[] comm) throws DukeException {
        if (comm.length == 1) {
            throw new DukeException("    Oh no! You have to specify which task to delete!");
        }

        Command c = new DeleteCommand(Integer.parseInt(comm[1]));
        return c;
    }

    public static Command parseTodo(String fullCommand, String[] comm) throws DukeException {
        // if no description is given
        if (comm.length == 1) {
            throw new DukeException("    Oh no! A todo cannot be empty!");
        }

        String[] arr = fullCommand.split("todo");
        Task t = new Todo(arr[1].trim(), arr[1]);

        Command c = new AddCommand(t);

        return c;
    }

    public static Command parseEvent(String fullCommand, String[] comm) throws DukeException {
        // if no description is given
        if (comm.length == 1) {
            throw new DukeException("    Oh no! An event cannot be empty!");
        }

        String[] arr = fullCommand.split("/at");
        // if no "at" is given
        if (arr.length == 1) {
            throw new DukeException("    Oh no! Please include an at!");
        }

        String[] arr2 = arr[0].split("event");
        String[] dateTime = arr[1].trim().split(" ");
        if (dateTime.length == 1) {
            throw new DukeException("    Oh no! Please include a time!");
        }

        String time = "";
        time = dateTime[1].trim();

        try {
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            Task t = new Event(arr2[1].trim(), time, date, comm[1]);
            Command c = new AddCommand(t);
            return c;

        } catch (DateTimeParseException e) {
            throw new DukeException("    Oh no! Please follow the date format! " +
                    "Example: 2020-01-27!");
        }
    }

    public static Command parseDeadline(String fullCommand, String[] comm) throws DukeException {
        // if no description is given
        if (comm.length == 1) {
            throw new DukeException("    Oh no! A deadline cannot be empty!");
        }

        String[] arr = fullCommand.split("/by");
        // if no "by" is given
        if (arr.length == 1) {
            throw new DukeException("    Oh no! You need to include by when!");
        }

        String[] arr2 = arr[0].split("deadline");
        String[] dateTime = arr[1].trim().split(" ");

        if (dateTime.length == 1) {
            throw new DukeException("    Oh no! Please include a time!");
        }

        String time = "";
        time = dateTime[1].trim();

        try {
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            // add to list
            Task t = new Deadline(arr2[1].trim(), time, date, comm[1]);
            Command c = new AddCommand(t);
            return c;

        } catch (DateTimeParseException e) {
            throw new DukeException("    Oh no! Please follow the date format! " +
                    "Example: 2020-01-27!");
        }
    }

}
