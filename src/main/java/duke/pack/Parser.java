package duke.pack;

import java.time.LocalDate;
import java.time.LocalTime;
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

        } else if (comm[0].equals("sort")) {
            // sort command to sort tasks chronologically
            return parseSort();

        } else if (comm[0].equals("done")) {
            // done command to mark task as done
            return parseDone(comm);

        } else if (comm[0].equals("delete")) {
            // delete command to remove task from list
            return parseDelete(comm);

        } else if (comm[0].equals("todo")) {
            // to-do command to add a to-do task
            return parseTodo(command, comm);

        } else if (comm[0].equals("event")) {
            // event command to add an event task
            return parseEvent(command, comm);

        } else if (comm[0].equals("deadline")) {
            // deadline command to add a deadline task
            return parseDeadline(command, comm);

        } else if (command.equals("bye")) {
            // exit command to close programme
            return new ExitCommand();

        } else {
            // invalid command
            throw new DukeException("Oh no! I'm sorry, I do not understand that, please try again!");
        }

    }

    /**
     * Handles sort command.
     * @return a SortCommand object
     */
    public static Command parseSort() {
        Command c = new SortCommand();
        return c;
    }

    /**
     * Interprets find command.
     * @param comm String array of the command
     * @return a FindCommand object
     * @throws DukeException if no search keyword was provided
     */
    public static Command parseFind(String[] comm) throws DukeException {
        if (comm.length == 1) {
            throw new DukeException("Oh no! You have to specify what you want to find!");
        }

        Command c = new FindCommand(comm[1]);
        return c;
    }

    /**
     * Interprets mark as done command.
     * @param comm String array of command
     * @return a DoneCommand object
     * @throws DukeException if task number is not specified
     */
    public static Command parseDone(String[] comm) throws DukeException {
        if (comm.length == 1) {
            throw new DukeException("Oh no! You have to specify which task is done!");
        }

        Command c = new DoneCommand(Integer.parseInt(comm[1]));
        return c;
    }

    /**
     * Interprets delete command.
     * @param comm String array of command
     * @return a DeleteCommand object
     * @throws DukeException if task number is not specified
     */
    public static Command parseDelete(String[] comm) throws DukeException {
        if (comm.length == 1) {
            throw new DukeException("Oh no! You have to specify which task to delete!");
        }

        Command c = new DeleteCommand(Integer.parseInt(comm[1]));
        return c;
    }

    /**
     * Interprets to-do command.
     * @param fullCommand String of full command given
     * @param comm String array of command
     * @return an AddCommand object
     * @throws DukeException if task description is not provided
     */
    public static Command parseTodo(String fullCommand, String[] comm) throws DukeException {
        // if no description is given
        if (comm.length == 1) {
            throw new DukeException("Oh no! A todo cannot be empty!");
        }

        String[] arr = fullCommand.split("todo");
        Task t = new Todo(arr[1].trim(), "0000-00-00", "00:00");

        Command c = new AddCommand(t);

        return c;
    }

    /**
     * Interprets an event command.
     * @param fullCommand String of full command given
     * @param comm String array of command
     * @return an AddCommand object
     * @throws DukeException if task description is not provided, date and time details are not included
     */
    public static Command parseEvent(String fullCommand, String[] comm) throws DukeException {
        // if no description is given
        if (comm.length == 1) {
            throw new DukeException("Oh no! An event cannot be empty!");
        }

        String[] splitByAt = fullCommand.split("/at");
        // if no "at" is given
        if (splitByAt.length == 1) {
            throw new DukeException("Oh no! Please include an at!");
        }
        
        String[] splitByEvent = splitByAt[0].split("event");
        String[] dateTime = splitByAt[1].trim().split(" ");
        if (dateTime.length == 1) {
            throw new DukeException("Oh no! Please include a time!");
        }

        try {
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            LocalTime time = LocalTime.parse(dateTime[1].trim());
            Task t = new Event(splitByEvent[1].trim(), time, date, date.toString(), time.toString());
            Command c = new AddCommand(t);
            return c;

        } catch (DateTimeParseException e) {
            throw new DukeException("Oh no! Please follow the date format! \n" +
                    "Example: 2020-01-27 13:00!");
        }
    }


    /**
     * Interprets a deadline command.
     * @param fullCommand String of full command given
     * @param comm String array of command
     * @return an AddCommand object
     * @throws DukeException if task description is not provided, date and time details are not included
     */
    public static Command parseDeadline(String fullCommand, String[] comm) throws DukeException {
        // if no description is given
        if (comm.length == 1) {
            throw new DukeException("Oh no! A deadline cannot be empty!");
        }

        String[] splitByBy = fullCommand.split("/by");
        // if no "by" is given
        if (splitByBy.length == 1) {
            throw new DukeException("Oh no! You need to include by when!");
        }

        String[] splitByDeadline = splitByBy[0].split("deadline");
        String[] dateTime = splitByBy[1].trim().split(" ");

        if (dateTime.length == 1) {
            throw new DukeException("Oh no! Please include a time!");
        }

        try {
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            LocalTime time = LocalTime.parse(dateTime[1].trim());
            // add to list
            Task t = new Deadline(splitByDeadline[1].trim(), time, date, date.toString(), time.toString());
            Command c = new AddCommand(t);
            return c;

        } catch (DateTimeParseException e) {
            throw new DukeException("Oh no! Please follow the date format! \n" +
                    "Example: 2020-01-27 13:00!");
        }
    }

}
