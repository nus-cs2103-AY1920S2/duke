package duke.logic;

import duke.command.AddCommand;
import duke.command.ChangeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingInfoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parses user inputs.
 */
public class Parser {

    /**
     * Throws an error if the given array only has 1 element.
     * @param task Type of task.
     * @param hasDescription Whether the task has a description.
     * @param elements Array to check.
     * @throws DukeException If given array has only 1 element.
     */
    private static void checkArr(String task, boolean hasDescription, String[] elements) throws DukeException {
        if (elements.length == 1) {
            throw new MissingInfoException(task, hasDescription);
        }
    }

    /**
     * Checks if an input command is valid.
     * @param input Input command.
     * @return true if input command is valid, false otherwise.
     */
    private static boolean isValidCommand(String input) {
        // Maintain an array of possible commands by the user
        ArrayList<String> commands = new ArrayList<>();
        commands.add("bye");
        commands.add("list");
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("delete");
        commands.add("done");
        commands.add("find");
        commands.add("edit");

        return commands.contains(input);
    }

    private static String getValidRegex(String str) {
        String regex = null;
        ArrayList<String> validRegexes = new ArrayList<>();
        validRegexes.add("/by");
        validRegexes.add("/at");

        for (String rgx: validRegexes) {
            if (str.contains(rgx)) {
                regex = rgx;
                break;
            }
        }

        return regex;
    }

    /**
     * Parses the given user input and maps it to a command.
     * @param input User input.
     * @return Command.
     * @throws DukeException If any error occurs during parsing of the user input.
     */
    public static Command parse(String input) throws DukeException {
        // Check for single word user inputs
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        }

        Command c;
        String[] inputElements = input.split(" ", 2);
        String command = inputElements[0];

        // Check for unknown commands
        if (!isValidCommand(command)) {
            return new UnknownCommand();
        }

        try {
            Parser.checkArr(command, false, inputElements);
            if (command.equals("todo")) {
                c = new AddCommand(new ToDo(inputElements[1]));
            } else if (command.equals("deadline")) {
                String[] descElements = inputElements[1].split(" /by ", 2);
                Parser.checkArr(command, true, descElements);
                LocalDate date = LocalDate.parse(descElements[1]);
                c = new AddCommand(new Deadline(descElements[0], date));
            } else if (command.equals("event")) {
                String[] descElements = inputElements[1].split(" /at ", 2);
                Parser.checkArr(command, true, descElements);
                LocalDate date = LocalDate.parse(descElements[1]);
                c = new AddCommand(new Event(descElements[0], date));
            } else if (command.equals("delete")) {
                int i = Integer.parseInt(inputElements[1]);
                c = new DeleteCommand(i - 1);
            } else if (command.equals("done")) {
                int i = Integer.parseInt(inputElements[1]);
                c = new DoneCommand(i - 1);
            } else if (command.equals("find")) {
                c = new FindCommand(inputElements[1]);
            } else if (command.equals("edit")) {
                String[] idxDescElements = inputElements[1].split(" ", 2);
                Parser.checkArr(command, false, idxDescElements);
                int i = Integer.parseInt(idxDescElements[0]);
                String regex = getValidRegex(idxDescElements[1]);
                // Editing Deadline or Event (expected, but not necessarily)
                if (regex != null) {
                    String[] descElements = idxDescElements[1].split(regex, 2);
                    LocalDate date = LocalDate.parse(descElements[1].trim());
                    // Only edit the time
                    if (descElements[0].trim().isEmpty()) {
                        c = new ChangeCommand(i - 1, date);
                    } else { // Edit description and time
                        c = new ChangeCommand(i - 1, descElements[0].trim(), date);
                    }
                } else { // Can be editing any task, but only the description
                    c = new ChangeCommand(i - 1, idxDescElements[1].trim());
                }
            } else {
                c = new UnknownCommand();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

        return c;
    }
}
