/**
 * Parser makes sense of the user command and calls
 * the respective command class
 */

package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;

public class Parser {

    public static String lastCommand;
    public static String lastDeleted;

    public Parser() {}

    public static Command parse(String command) throws DukeException {


        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];

        if (command.equals("bye")) {
            lastCommand = command;
            return new ExitCommand(command);
        } else if (command.equals("list")) {
            lastCommand = command;
            return new ListCommand(command);
        } else if (firstWord.equals("done")) {
            lastCommand = command;
            return new DoneCommand(command);
        } else if (firstWord.equals("delete")) {
            lastCommand = command;
            return new DeleteCommand(command);
        } else if (firstWord.equals("find")) {
            lastCommand = command;
            return new FindCommand(command);
        } else if (firstWord.equals("todo")) {
            lastCommand = command;
            return new AddCommand(command);
        } else if (firstWord.equals("deadline")) {
            lastCommand = command;
            return new AddCommand(command);
        } else if (firstWord.equals("event")) {
            lastCommand = command;
            return new AddCommand(command);
        } else if (firstWord.equals("undo")) {
            return new UndoCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}