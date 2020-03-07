package com.duke.util;

import com.duke.command.Command;
import com.duke.command.DeleteCommand;
import com.duke.command.DoneCommand;
import com.duke.command.ExitCommand;
import com.duke.command.FindCommand;
import com.duke.command.FindTagCommand;
import com.duke.command.ListCommand;
import com.duke.command.TagCommand;
import com.duke.command.TaskCommand;
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
     *
     * @param cmd String representation of user input
     * @return Command representation of the user input
     * @throws DukeException when the user input is invalid.
     */
    public static Command parse(String cmd) throws DukeException {
        StringTokenizer st = new StringTokenizer(cmd);
        String firstToken = st.nextToken();
        Command output = null;

        if (cmd.equals("bye")) {
            output = new ExitCommand();
        } else if (cmd.equals("list")) {
            output = new ListCommand();
        } else if (firstToken.equals("delete")) {
            try {
                int index = Integer.parseInt(cmd.substring(7)) - 1;
                output = new DeleteCommand(index);
            } catch (Exception e) {
                throw new DukeException("OOPS! delete should follow by a number");
            }
        } else if (firstToken.equals("done")) {
            try {
                int index = Integer.parseInt(cmd.substring(5)) - 1;
                output = new DoneCommand(index);
            } catch (Exception e) {
                throw new DukeException("OOPS! done should follow by a number");
            }

        } else if (firstToken.equals("find")) {
            try {
                cmd = cmd.substring(5);
                if (cmd.equals("")) {
                    throw new DukeException("OOPS! Please specify keyword to find");
                }
                output = new FindCommand(cmd);
            } catch (Exception e) {
                throw new DukeException("OOPS! Please specify keyword to find");
            }
        } else if (firstToken.equals("tag")) {
            try {
                cmd = cmd.substring(4);
                if (cmd.equals("")) {
                    throw new DukeException("Enter 'tag <tag> <task_index>' to add tag"
                            + " or 'tag <tag>' to query");
                }
                String[] temp = cmd.split(" ");

                if (temp.length == 1) {
                    String tag = temp[0];

                    output = new FindTagCommand(tag);
                } else if (temp.length == 2) {
                    String tag = temp[0];
                    int num = Integer.parseInt(temp[1]);
                    output = new TagCommand(tag, num);
                } else {
                    throw new DukeException("Enter 'tag <tag> <task_index>' to add tag"
                            + " or 'tag <tag>' to query");
                }
            } catch (DukeException e) {
                throw e;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Enter 'tag <tag> <task_index>' to add tag"
                        + " or 'tag <tag>' to query");
            }
        }


        else {
            
            Task itemToAdd = null;

            if (firstToken.equals("deadline")) {
                try {
                    cmd = cmd.substring(9);
                    String[] temp = cmd.split(" /by ");
                    itemToAdd = new Deadline(temp[0], temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Wrong input format for deadline");
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Wrong format of time, try yyyy-mm-dd");
                }

            } else if (firstToken.equals("event")) {
                try {
                    cmd = cmd.substring(6);
                    String[] temp = cmd.split(" /at ");
                    itemToAdd = new Event(temp[0], temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Wrong input format for event");
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Wrong format of time, try yyyy-mm-dd");
                }
            } else if (firstToken.equals("todo")) {
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
