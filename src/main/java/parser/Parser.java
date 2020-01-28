package parser;

import task.*;
import command.*;
import ui.TextUi;
import common.Message;
import common.Storage;
import exception.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ");
        String[] stamps = input.split("/");
        String keyword = words[0];
        if (keyword.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (input.equalsIgnoreCase("list")) {
            return new DisplayCommand();
        }
        int thisIndex = -1;
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
            //System.out.println("it is a todo task, i am adding it.");
            return new AddCommand(new Todo(words[1]));
        } else if (keyword.equalsIgnoreCase("event")) {
            try {
                if (words.length < 4 || stamps.length < 2) {
                    throw new DukeException(Message.MESSAGE_INVALIDCOMMAND);
                }
            } catch (DukeException exp) {
                return new ErrorCommand();
            }
            String[] eventWords = input.split("/at ");
            return new AddCommand(new Event(stamps[0].substring(6), eventWords[1]));
        } else if (keyword.equalsIgnoreCase("deadline")) {
            try {
                if (words.length < 4 || stamps.length < 2) {
                    throw new DukeException("The content of a deadline must be complete.");
                }
            } catch (DukeException exp) {
                return new ErrorCommand();
            }
            String[] ddlWords = input.split("/by ");
            return new AddCommand(new Deadline(stamps[0].substring(9), ddlWords[1]));
        } else {
            return new ErrorCommand();
        }
    }
}
