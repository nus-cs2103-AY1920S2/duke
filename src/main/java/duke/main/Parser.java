package duke.main;

import duke.command.ByeCommand;
import duke.command.ClearAllCompleteCommand;
import duke.command.Command;
import duke.command.CreateDeadlineCommand;
import duke.command.CreateEventCommand;
import duke.command.CreateTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;

public class Parser {

    /**
     * Returns a different Command according to input by user. Different commands
     * get executed differently.
     * 
     * @param input Input from user to be parsed as a Command
     * @return User Command
     */
    public Command parse(String input) {
        String[] inputArr = input.split(" ");
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (inputArr[0].equals("list")) {
            return new ListCommand(inputArr);
        } else if (inputArr[0].equals("done")) {
            return new DoneCommand(inputArr);
        } else if (inputArr[0].equals("find")) {
            return new FindCommand(inputArr);
        } else if (inputArr[0].equals("delete")) {
            if (inputArr[1].equals("complete")) {
                return new ClearAllCompleteCommand(inputArr);
            } else {
                return new DeleteCommand(inputArr);
            }
        } else if (inputArr[0].equals("todo")) {
            return new CreateTodoCommand(inputArr);
        } else if (inputArr[0].equals("event")) {
            return new CreateEventCommand(inputArr);
        } else if (inputArr[0].equals("deadline")) {
            return new CreateDeadlineCommand(inputArr);
        } else {
            return new InvalidCommand();
        }
    }
}