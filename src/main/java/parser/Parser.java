package parser;

import command.AddCommand;
import command.Command;
import command.DeleteAllCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;

import dukeexception.DukeException;

import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Represents a Parser class which parses the command and makes sense of the input string into commands.
 */
public class Parser {

    /**
     * Method to return if the string is actually an integer
     * @param string input string
     * @return boolean to determine if string is numeric
     */
    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            int value = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static Command parseCommand(String userInput) throws DukeException {
        String[] split = userInput.split(" ", 2);
        String first = split[0];
        switch (first) {
        case "bye":
            return new ExitCommand();

        case "done":
            int doneId = Integer.parseInt(split[1]);
            return new DoneCommand(doneId);

        case "delete":
            if (isNumeric(split[1])) {
                int deleteId = Integer.parseInt(split[1]);
                return new DeleteCommand(deleteId);
            } else if (split[1].equals("all")) {
                return new DeleteAllCommand();
            }

        case "list":
            return new ListCommand();

        case "event": // event some meeting /at 2020-01-01
            if (split.length < 2) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            } else {
                String[] splitTaskName = split[1].split("/"); // to obtain command when splitting
                String eventDescription = splitTaskName[0];
                String[] splitTaskName2 = splitTaskName[1].split(" ", 2); // to obtain date when splitting
                String eventDate = splitTaskName2[1];
                return new AddCommand(new Event(eventDescription, eventDate));
            }

        case "deadline":
            if (split.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String[] splitTaskName3 = split[1].split("/"); // to obtain command when splitting
                String deadlineDescription = splitTaskName3[0];
                String[] splitTaskName4 = splitTaskName3[1].split(" ", 2); // to obtain date when splitting
                String deadlineDate = splitTaskName4[1];
                return new AddCommand(new Deadline(deadlineDescription, deadlineDate));
            }

        case "todo":
            if (split.length < 2) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddCommand(new Todo(split[1]));
            }

        case "find":
            String keyword = split[1];
            return new FindCommand(keyword);

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}