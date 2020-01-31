package duke;


import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * The type Parser .
 */
public class Parser {

    /**
     * Parse command form the user and pass it to the various command classes.
     *
     * @param fullCommand the full command
     * @param userInput   the user input
     * @return the associated command
     * @throws DukeException the duke exception
     */
    static Command parse(String fullCommand, String userInput) throws DukeException {
        if (fullCommand.contains("Add")) {
            return new AddCommand(userInput);
        } else if (fullCommand.contains("Bye")) {
            return new ByeCommand(userInput);
        } else if (fullCommand.contains("Delete")) {
            return new DeleteCommand(userInput);
        } else if (fullCommand.contains("List")) {
            return new ListCommand(userInput);
        } else if (fullCommand.contains("Done")) {
            return new DoneCommand(userInput);
        } else if (fullCommand.contains("Find")) {
            return new FindCommand(userInput);
        } else {
            throw new DukeException("Please input a correct command");
        }
    }

}
