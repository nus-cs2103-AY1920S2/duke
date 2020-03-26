package duke.util; 

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    /**
     * Parses a string input and return the command
     * (one of the subclasses of {@link duke.command.Command}) the input represents.
     * 
     * @param input String input to be parsed
     * @return A command the string input represents
     * @throws DukeException Throws if invalid input string is received
     */
    public static Command parse(String input) throws DukeException {
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return parseDoneInput(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteInput(input);
        } else if (input.startsWith("find")) {
            return parseFindInput(input);
        } else if (input.startsWith("todo")) {
            return parseTodoInput(input);
        } else if (input.startsWith("event")) {
            return parseEventInput(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineInput(input);
        } else if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else {
            throw new DukeException(String.format("Oops, '%s' is an invalid input", input));
        }
    }

    private static Command parseDoneInput(String input) throws DukeException {
        Pattern donePattern = Pattern.compile("^done\\s+(\\d+)$");
        Matcher doneMatcher = donePattern.matcher(input);
        if (doneMatcher.matches()) {
            int doneIndex = Integer.parseInt(doneMatcher.group(1)) - 1;
            return new DoneCommand(doneIndex);
        } else {
            throw new DukeException("Oops, invalid done command");
        }
    }

    private static Command parseDeleteInput(String input) throws DukeException {
        Pattern deletePattern = Pattern.compile("^delete\\s+(\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);
        if (deleteMatcher.matches()) {
            int deleteIndex = Integer.parseInt(deleteMatcher.group(1)) - 1;
            return new DeleteCommand(deleteIndex);
        } else {
            throw new DukeException("Oops, invalid delete command");
        }
    } 

    private static Command parseFindInput(String input) throws DukeException {
        Pattern findPattern = Pattern.compile("^find\\s+(.+)$");
        Matcher findMatcher = findPattern.matcher(input);
        if (findMatcher.matches()) {
            return new FindCommand(findMatcher.group(1));
        } else {
            throw new DukeException("Oops, invalid find command");
        }
    }

    private static Command parseTodoInput(String input) throws DukeException {
        Pattern todoPattern = Pattern.compile("^todo\\s+(.+)$");
        Matcher todoMatcher = todoPattern.matcher(input);
        if (todoMatcher.matches()) {
            Todo newTodo = new Todo(todoMatcher.group(1));
            return new AddCommand(newTodo);
        } else {
            throw new DukeException("Oops, invalid todo command");
        }
    }

    private static Command parseEventInput(String input) throws DukeException {
        Pattern eventPattern = Pattern.compile("^event\\s+(.+)\\s+/at\\s+(\\d{4}-\\d{2}-\\d{2})$");
        Matcher eventMatcher = eventPattern.matcher(input);
        if (eventMatcher.matches()) {
            LocalDate dateAt = LocalDate.parse(eventMatcher.group(2));
            Event newEvent = new Event(eventMatcher.group(1), dateAt);
            return new AddCommand(newEvent);
        } else {
            throw new DukeException("Oops, invalid event command");
        }
    }

    private static Command parseDeadlineInput(String input) throws DukeException {
        Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$");
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        if (deadlineMatcher.matches()) {
            LocalDate dateBy = LocalDate.parse(deadlineMatcher.group(2));
            Deadline newDeadline = new Deadline(deadlineMatcher.group(1), dateBy);
            return new AddCommand(newDeadline);
        } else {
            throw new DukeException("Oops, invalid deadline command");
        }
    }
}
