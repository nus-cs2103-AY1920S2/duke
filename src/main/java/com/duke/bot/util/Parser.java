package com.duke.bot.util; 

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.duke.bot.Deadline;
import com.duke.bot.DukeException;
import com.duke.bot.Event;
import com.duke.bot.Todo;
import com.duke.bot.command.AddCommand;
import com.duke.bot.command.Command;
import com.duke.bot.command.DeleteCommand;
import com.duke.bot.command.DoneCommand;
import com.duke.bot.command.ExitCommand;
import com.duke.bot.command.ListCommand;

public class Parser {
    public static Command parse(String input) throws DukeException {
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return parseDoneInput(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteInput(input);
        } else if (input.startsWith("todo")) {
            return parseTodoInput(input);
        } else if (input.startsWith("event")) {
            return parseEventInput(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineInput(input);
        } else if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException(String.format("☹ OOPS!!! '%s' is an invalid input.", input));
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
