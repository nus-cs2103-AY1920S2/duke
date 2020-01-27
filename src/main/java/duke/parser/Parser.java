package duke.parser;

import duke.DukeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] command = fullCommand.split(" ", 2);
        Command toReturn;
        switch (command[0]) {
        case "bye":
            toReturn = new ExitCommand();
            break;
        case "list":
            toReturn = new ListCommand();
            break;
        case "delete":
            try {
                toReturn = new DeleteCommand(Integer.parseInt(command[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Which task should I remove?");
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please give me the task number to delete.");
            }
            break;
        case "done":
            try {
                toReturn = new DoneCommand(Integer.parseInt(command[1]) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please give me the task number.");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Which task is done?");
            }
            break;
        case "todo":
            try {
                toReturn = new AddCommand(new Todo(command[1]));
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "event":
            try {
                String[] info = command[1].split(" /at ");
                toReturn = new AddCommand(new Event(info[0], LocalDate.parse(info[1])));
            } catch (DateTimeException e) {
                throw new DukeException("OOPS!!! Please give me the date in yyyy-mm-dd format!");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Missing information regarding event.");
            }
            break;
        case "deadline":
            try {
                String[] info = command[1].split(" /by ");
                toReturn = new AddCommand(new Deadline(info[0], LocalDate.parse(info[1])));
            } catch (DateTimeException e) {
                throw new DukeException("OOPS!!! Please give me the date in yyyy-mm-dd format!");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Missing information regarding deadline.");
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return toReturn;
    }
}
