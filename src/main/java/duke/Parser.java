package duke;

import command.Command;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ErrorCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class takes care to interpret information from user's input.
 **/
public class Parser {

    /**
     * Retrieves the command object to be generated
     * according to user's input.
     * @return Command object of the input from the user.
     **/
    public static Command parse(String command) {
        String trimCommand = command.trim();
        String firstCommand;
        if (command.trim().contains(" ")) {
            firstCommand = trimCommand.split(" ")[0];
        } else {
            firstCommand = command.trim();
        }
        if (firstCommand.equals("list")) {
            return new ListCommand();
        } else if (firstCommand.equals("bye")) {
            return new ExitCommand();
        } else if (firstCommand.equals("find")) {
            try {
                return new FindCommand(Parser.getSecond(trimCommand));
            } catch (IndexOutOfBoundsException e) {
                return new ErrorCommand(new DukeException(
                        "☹ OOPS!!! The keyword of a find cannot be empty."));
            }
        } else if (firstCommand.equals("done")) {
            try {
                return new DoneCommand(Integer.valueOf(Parser.getSecond(trimCommand)));
            } catch (IndexOutOfBoundsException e) {
                return new ErrorCommand(new DukeException(
                        "☹ OOPS!!! The description of a done cannot be empty."));
            }
        } else if (firstCommand.equals("delete")) {
            try {
                return new DeleteCommand(Integer.valueOf(Parser.getSecond(trimCommand)));
            } catch (IndexOutOfBoundsException e) {
                return new ErrorCommand(new DukeException(
                        "☹ OOPS!!! The description of a delete cannot be empty."));
            }
        } else {
            if (firstCommand.equals("todo")) {
                try {
                    return new AddToDoCommand(Parser.getTaskName(trimCommand));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a todo cannot be empty."));
                }
            } else if (firstCommand.equals("deadline")) {
                try {
                    return new AddDeadlineCommand(Parser.getTaskName(trimCommand), Parser.getDate(trimCommand));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a deadline cannot be empty."));
                }
            } else if (firstCommand.equals("event")) {
                try {
                    return new AddEventCommand(Parser.getTaskName(trimCommand), Parser.getDate(trimCommand));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of an event cannot be empty."));
                }
            } else {
                return new ErrorCommand(new DukeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
        }
    }

    /**
     * Retrieves the second argument of the command.
     * @return String of the second argument of the command
     **/
    public static String getSecond(String text) {
        return text.trim().split(" ")[1];
    }

    /**
     * Retrieves the third argument of the command.
     * @return String of the third argument of the command
     **/
    public static String getTaskName(String text) {
        String taskType = text.trim().split(" ")[0];
        if (text.contains("/")) {
            assert taskType.equals("deadline") || taskType.equals("event");
            return text.substring(text.indexOf(" ") + 1,
                    text.indexOf("/") - 1);
        } else {
            assert taskType.equals("todo") : taskType;
            return text.trim().substring(5);
        }
    }

    /**
     * Retrieves the date format of the command.
     * @return Date format of the of the command of the task
     **/
    public static LocalDateTime getDate(String text) {
        String beforeDate = text.substring(text.indexOf("/") + 1, text.indexOf("/") + 4);
        assert beforeDate.equals("/by ");
        return extractDate(text.substring(text.indexOf("/") + 4));
    }

    /**
     * Converts a date in the form of a string to a Date form.
     * @return Date format of the of the string format of the date
     **/
    static LocalDateTime extractDate(String next) {
        StringBuilder forTime = new StringBuilder(next.substring(next.indexOf(" ") + 1));
        forTime.insert(2, ':');
        String[] reverse = next.substring(0, next.indexOf(" "))
                .split("/");
        String[] reversed = reverse;
        String temp = String.format("%2s",reverse[0]).replace(" ", "0");
        reversed[0] = String.format("%4s",reverse[2]).replace(" ", "0");
        reversed[2] = temp;
        reversed[1] = String.format("%2s", reverse[1]).replace(" ", "0");
        String date = String.join("-", reversed);
        LocalDateTime taskDate = LocalDate.parse(date)
                .atTime(LocalTime.parse(forTime.toString()))
                ;
        return taskDate;
    }

}
