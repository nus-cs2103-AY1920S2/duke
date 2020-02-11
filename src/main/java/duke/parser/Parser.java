/**
 * Parser makes sense of the user command and calls
 * the respective command class
 */

package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;

public class Parser {

    public Parser() {}

    public static Command parse(String command) {

        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];

        try {
            if (command.equals("bye")) {
                return new ExitCommand(command);
            } else if (command.equals("list")) {
                return new ListCommand(command);
            } else if (firstWord.equals("done")) {
                return new DoneCommand(command);
            } else if (firstWord.equals("delete")) {
                return new DeleteCommand(command);
            } else if (firstWord.equals("find")) {
                return new FindCommand(command);
            } else if (firstWord.equals("todo")) {
                return new AddCommand(command);
            } else if (firstWord.equals("deadline")) {
                return new AddCommand(command);
            } else if (firstWord.equals("event")) {
                return new AddCommand(command);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        //catch exceptions
        catch (DukeException e){
            System.out.print(e.getMessage());
        }
        return new NullCommand(command);
    }
}