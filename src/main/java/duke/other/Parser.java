package duke.other;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;


/**
 * Represents a Parser that extracts out the command type and its details from a full String command input by the user.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Returns a Command object corresponding to the command type extracted.
     *
     * @param fullCommand Full String input by the user
     * @return Command object corresponding to the command type(i.e.TodoCommand if command type is Todo)
     * @throws DukeException If the command type is invalid (i.e. hi, why)
     */
    public static Command parse(String fullCommand)  {
        String[] replyArr = fullCommand.split(" ");
        String instruction = replyArr[0];
        switch (instruction) {
        case "bye":
            ByeCommand c = new ByeCommand();
            return c;
        case "date":

            return new DateCommand(instruction, replyArr);
        case "deadline":
            return new DeadlineCommand(instruction, stringifyArray(replyArr));
        case "delete":
            return new DeleteCommand(instruction, replyArr);
        case "done":
            return new DoneCommand(instruction, replyArr);
        case "event":
            return new EventCommand(instruction, stringifyArray(replyArr));
        case "find":
            return new FindCommand(instruction, replyArr);
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand(instruction, replyArr);
        case "todo":
            return new TodoCommand(stringifyArray(replyArr));
        default:
            return new UnknownCommand(instruction);
        }
    }

    /**
     * Returns the details of the command without the command type in a String.
     *
     * @param replyArr Array of String of the full command input by the user, split by " "
     * @return Returns a String of the details of the command
     */
    public static String stringifyArray(String[] replyArr) {
        String details = "";
        for (int i = 1; i < replyArr.length; i++) {
            details += replyArr[i] + " ";
        }
        return details;
    }
}
