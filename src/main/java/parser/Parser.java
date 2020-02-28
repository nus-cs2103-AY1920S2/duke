package parser;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.DisplayCommand;
import command.ErrorCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ReminderCommand;
import task.Todo;
import task.Event;
import task.Deadline;
import common.Message;
import exception.DukeException;

/**
 * Represents a parser between command input and command objects.
 */
public class Parser {

    /**
     * Converts user inputs to command objects.
     *
     * @param input the input string of the user
     * @return a command object
     */
    public static Command parse(String input) {
        String[] words = input.split(" ");
        String[] stamps = input.split("/");
        String keyword = words[0];
        if(input.equals("")){
            return new ErrorCommand();
        }
        if (keyword.equalsIgnoreCase("find")) {
            if (words.length < 2) {
                return new ErrorCommand();
            }
            String searchingItem = input.substring(5).trim();
            return new FindCommand(searchingItem);
        }

        if (keyword.equalsIgnoreCase("help")) {
            return new HelpCommand();
        }

        if(keyword.equalsIgnoreCase("reminders")) {
            return handleReminder(words);
        }

        if (keyword.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (input.equalsIgnoreCase("list")) {
            return new DisplayCommand();
        }
        int thisIndex;
        if (keyword.equalsIgnoreCase("done")) {
            try {
                if (words.length != 2) {
                    throw new DukeException(Message.MESSAGE_INVALIDCOMMAND);
                }
                thisIndex = Integer.parseInt(input.substring(5));
            } catch (Exception exp) {
                return new ErrorCommand();
            }
            return new DoneCommand(thisIndex);
        } else if (keyword.equalsIgnoreCase("delete")) {
            try {
                if (words.length != 2) {
                    throw new DukeException(Message.MESSAGE_INVALIDCOMMAND);
                }
                thisIndex = Integer.parseInt(input.substring(7));
            } catch (Exception exp) {
                return new ErrorCommand();
            }
            return new DeleteCommand(thisIndex);
        } else if (keyword.equalsIgnoreCase("todo")) {
            try {
                if (words.length < 2) {
                    throw new DukeException(Message.MESSAGE_INVALIDCOMMAND);
                }
            } catch (DukeException exp) {
                return new ErrorCommand();
            }
            return new AddCommand(new Todo(input.substring(5).trim()));
        } else if (keyword.equalsIgnoreCase("event")) {
            String[] eventWords = input.split("/at ");
            try {
                if (words.length < 4 || stamps.length < 2 || eventWords.length < 2) {
                    throw new DukeException(Message.MESSAGE_INVALIDCOMMAND);
                }
            } catch (DukeException exp) {
                return new ErrorCommand();
            }
            return new AddCommand(new Event(stamps[0].substring(6), eventWords[1]));
        } else if (keyword.equalsIgnoreCase("deadline")) {
            String[] ddlWords = input.split("/by ");
            try {
                if (words.length < 4 || stamps.length < 2 || ddlWords.length < 2) {
                    throw new DukeException("The content of a deadline must be complete.");
                }
            } catch (DukeException exp) {
                return new ErrorCommand();
            }
            return new AddCommand(new Deadline(stamps[0].substring(9), ddlWords[1]));
        } else {
            return new ErrorCommand();
        }
    }

    public static Command handleReminder(String[] words) {
        if(words.length == 1){
            return new ReminderCommand();
        } else if (words.length == 2){
            String taskType = words[1];
            if (taskType.equalsIgnoreCase("todo")) {
                return new ReminderCommand(1);
            } else if (taskType.equalsIgnoreCase("event")) {
                return new ReminderCommand(2);
            } else if (taskType.equalsIgnoreCase("deadline")) {
                return new ReminderCommand(3);
            } else {
                return new ErrorCommand();
            }
        } else {
            return new ErrorCommand();
        }
    }
}
