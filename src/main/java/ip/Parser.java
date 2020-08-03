package ip;

import ip.command.*;
import ip.task.Deadline;
import ip.task.Event;
import ip.task.FixedDuration;
import ip.task.Todo;

import java.time.LocalDate;
import java.util.Scanner;


public class Parser {
    /**
     * Parses a command entered in the command line and returns a command
     * @param input the string to be parsed
     * @return the command to be executed
     */
    public Command parse(String input) {
        Scanner inputSc;
        String command, name, datetime, rest;
        inputSc = new Scanner(input);
        try {
            if (!inputSc.hasNext()) {
                return null;
            }
            command = inputSc.next();
            switch (command) {
                case "bye": return new ExitCommand();
                case "list": return new ListCommand();
                case "find":
                    if (!inputSc.hasNext()){
                        throw new Duke.DukeException("The keyword to search is missing :p");
                    }
                    return new FindCommand(inputSc.nextLine().trim());
                case "done":
                    if (!inputSc.hasNextInt()) {
                        throw new Duke.DukeException("The index of the task is missing :/");
                    }
                    int index = inputSc.nextInt() - 1;
                    return new DoneCommand(index);
                case "delete":
                    if (!inputSc.hasNextInt()) {
                        throw new Duke.DukeException("The index of the task is missing :/");
                    }
                    int i = inputSc.nextInt() - 1;
                    return new DeleteCommand(i);
                case "todo":
                    name = input.substring(command.length()).trim();
                    if (name.length() == 0) {
                        throw new Duke.DukeException("The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(name);
                    return new AddCommand(todo);
                case "deadline":
                    rest = input.substring(command.length()).trim();
                    int byIndex = rest.indexOf("/by");
                    if (byIndex == -1) {
                        throw new Duke.DukeException("Please include the deadline datetime after the \"/by\" keyword");
                    }
                    name = rest.substring(0, byIndex).trim();
                    if (name.length() == 0) {
                        throw new Duke.DukeException("The description of a deadline cannot be empty.");
                    }
                    datetime = rest.substring(byIndex + 3).trim();
                    if (datetime.length() == 0) {
                        throw new Duke.DukeException("The deadline datetime cannot be empty.");
                    }
                    Deadline dl;
                    try {
                        dl = new Deadline(name, LocalDate.parse(datetime));
                        return new AddCommand(dl);
                    } catch (Exception e) {
                        dl = new Deadline(name, (datetime));
                        return new AddCommand(dl);
                    }
                case "event":
                    rest = input.substring(command.length()).trim();
                    int atIndex = rest.indexOf("/at");
                    if (atIndex == -1) {
                        throw new Duke.DukeException("Please include the event datetime after the \"/at\" keyword");
                    }
                    name = rest.substring(0, atIndex).trim();
                    if (name.length() == 0) {
                        throw new Duke.DukeException("The description of a event cannot be empty.");
                    }
                    datetime = rest.substring(atIndex + 3).trim();
                    if (datetime.length() == 0) {
                        throw new Duke.DukeException("The event datetime cannot be empty.");
                    }
                    Event ev;
                    try {
                        ev = new Event(name, LocalDate.parse(datetime));
                        return new AddCommand(ev);
                    } catch (Exception e) {
                        ev = new Event(name, (datetime));
                        return new AddCommand(ev);
                    }
                case "fixed":
                    rest = input.substring(command.length()).trim();
                    int durationIndex = rest.indexOf("/duration");
                    if (durationIndex == -1) {
                        throw new Duke.DukeException("Please include the duration after the \"/duration\" keyword");
                    }
                    name = rest.substring(0, durationIndex).trim();
                    if (name.length() == 0) {
                        throw new Duke.DukeException("The description of a fixed duration task cannot be empty.");
                    }
                    datetime = rest.substring(durationIndex + 9).trim();
                    if (datetime.length() == 0) {
                        throw new Duke.DukeException("The duration cannot be empty.");
                    }
                    return new AddCommand(new FixedDuration(name, datetime));
                default:
                    throw new Duke.DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Duke.DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
