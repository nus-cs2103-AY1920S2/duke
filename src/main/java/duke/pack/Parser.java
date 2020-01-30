package duke.pack;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * represents a parser to interpret the user's commands
 */
public class Parser {
    public Parser() {
    }

    /**
     * Interprets the user's command
     * @param command input of the user
     * @return a Command that indicates what the user wants
     * @throws DukeException if command is not correctly inputted
     */
    public Command parseCommand(String command) throws DukeException {
        String[] comm = command.split(" ");

        if (command.equals("list")) {
            // list
            Command c = new ListCommand();
            return c;

        } else if (comm[0].equals("find")) {
            // find
            if (comm.length == 1) {
                throw new DukeException("    Oh no! You have to specify what you want to find!");
            }

            Command c = new FindCommand(comm[1]);
            return c;

        } else if (comm[0].equals("done")) {
            // done
            if (comm.length == 1) {
                throw new DukeException("    Oh no! You have to specify which task is done!");
            }

            Command c = new DoneCommand(Integer.parseInt(comm[1]));
            return c;

        } else if (comm[0].equals("delete")) {
            // delete
            if (comm.length == 1) {
                throw new DukeException("    Oh no! You have to specify which task to delete!");
            }

            Command c = new DeleteCommand(Integer.parseInt(comm[1]));
            return c;

        } else if (comm[0].equals("todo")) {
            // to-do
            // if no description is given
            if (comm.length == 1) {
                throw new DukeException("    Oh no! A todo cannot be empty!");
            }

            String[] arr = command.split("todo");
            Task t = new Todo(arr[1].trim(), arr[1]);

            Command c = new AddCommand(t);
            return c;

        } else if (comm[0].equals("event")) {
            // event
            // if no description is given
            if (comm.length == 1) {
                throw new DukeException("    Oh no! An event cannot be empty!");
            }

            String[] arr = command.split("/at");
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

        } else if (comm[0].equals("deadline")) {
            // deadline
            // if no description is given
            if (comm.length == 1) {
                throw new DukeException("    Oh no! A deadline cannot be empty!");
            }

            String[] arr = command.split("/by");
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

        } else if (command.equals("bye")) {
            Command c = new ExitCommand();
            return c;

        } else {
            // invalid command
            throw new DukeException("    Oh no! I'm sorry, I do not understand that, please try again!");
        }

    }

}
