package com.duke.util;

import com.duke.command.*;
import com.duke.task.Deadline;
import com.duke.task.Event;
import com.duke.task.Task;
import com.duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

/**
 * Represents a handler of the user input command which converts <code>String</code>
 * commands into actual <code>Command</code> objects to be executed.
 */
public class Parser {
    /**
     * Takes in a <code>String</code> form of command and converts it into <code>Command</code>
     * object to be executed.
     * @param cmd String representation of user input
     * @return Command representation of the user input
     * @throws DukeException when the user input is invalid.
     */
    public static Command parse(String cmd) throws DukeException {
        StringTokenizer st = new StringTokenizer(cmd);
        String first_token = st.nextToken();
        Command output;

        if (cmd.equals("bye")) {
            output = new ExitCommand();
        } else if (cmd.equals("list")){
            output = new ListCommand();
        } else if (first_token.equals("delete")) {
            try {
                int index = Integer.parseInt(cmd.substring(7)) - 1;
                output = new DeleteCommand(index);
            } catch (Exception e) {
                throw new DukeException("OOPS! delete should follow by a number");
            }
        } else if (first_token.equals("done")) {
            try {
                int index = Integer.parseInt(cmd.substring(5)) - 1;
                output = new DoneCommand(index);
            } catch (Exception e) {
                throw new DukeException("OOPS! done should follow by a number");
            }

        } else {
            Task itemToAdd = null;

            if (first_token.equals("deadline")) {
                try {
                    cmd = cmd.substring(9);
                    String[] temp = cmd.split(" /by ");
                    itemToAdd = new Deadline(temp[0], temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Wrong input format for deadline");
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Wrong format of time, try yyyy-mm-dd");
                }

            } else if (first_token.equals("event")) {
                try {
                    cmd = cmd.substring(6);
                    String[] temp = cmd.split(" /at ");
                    itemToAdd = new Event(temp[0], temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Wrong input format for event");
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Wrong format of time, try yyyy-mm-dd");
                }
            } else if (first_token.equals("todo")) {
                try {
                    cmd = cmd.substring(5);
                    itemToAdd = new Todo(cmd);
                    if (cmd.equals("")) {
                        throw new Exception("empty todo");
                    }
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
            }

            if (itemToAdd == null) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            output = new TaskCommand(itemToAdd);
        }
        return output;
    }
}
