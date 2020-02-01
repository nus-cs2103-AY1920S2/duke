import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {


    public static Command parseCommand(String input) throws DukeException{
        Command comm;
        try {
            comm = Command.valueOf(input.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new DukeException("Charmander hurt itself in its confusion!");
        }

        return comm;
    }

    public static int parseIndex(String input) throws DukeException {
        int index = -1;
        try {
            index = Integer.parseInt(input.split(" ", 2)[1]);
        } catch (NumberFormatException err) {
            throw new DukeException("Charmander needs a number!");
        }

        return index;
    }

    public static Task parseTask(String input) throws DukeException{
        Task newTask;
        Command comm = parseCommand(input);
        String desc;
        String time;

        try {
            desc = input.split(" ", 2)[1];

            switch (comm) {
                case TODO:
                    newTask = new Todo(desc);
                    break;
                case EVENT:
                    try {
                        time = desc.split(" /by ", 2)[1];
                        desc = desc.split(" /by ", 2)[0];
                        newTask = new Event(desc, LocalDate.parse(time));
                    } catch (ArrayIndexOutOfBoundsException err) {
                        throw new DukeException("Charmander needs a description AND a date to write it down!");
                    } catch (DateTimeParseException err) {
                        throw new DukeException("Place date by yyyy-mm-dd format!");
                    }
                    break;
                default: // for DEADLINES
                    try {
                        time = desc.split(" /by ", 2)[1];
                        desc = desc.split(" /by ", 2)[0];
                        newTask = new Deadline(desc, LocalDate.parse(time));
                    } catch (ArrayIndexOutOfBoundsException err) {
                        throw new DukeException("Charmander needs a description AND a date to write it down!");
                    } catch (DateTimeParseException err) {
                        throw new DukeException("Place date by yyyy-mm-dd format!");
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Charmander needs a todo description to write it down!");
        }

        return newTask;
    }

}
