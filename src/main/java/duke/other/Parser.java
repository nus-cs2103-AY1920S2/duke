package duke.other;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ShowCommand;

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
     * @return Command object corresponding to the command type(i.e.AddCommand if command type:Todo, Event or Deadline)
     * @throws DukeException If the command type is invalid (i.e. hi, why)
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] replyArr = fullCommand.split(" ");
        String instruction = replyArr[0];
        switch (instruction) {
        case "bye":
            Command c = new AddCommand(instruction, stringifyDetails(replyArr));
            c.isExit = true;
            System.out.println("    Bye! See ya later, alligator!");
            return c;
        case "delete":
            return new DeleteCommand(instruction, replyArr);
        case "deadline":
        case "todo":
        case "event":
            return new AddCommand(instruction, stringifyDetails(replyArr));
        case "list":
        case "date":
        case "done":
        case "find":
            return new ShowCommand(instruction, replyArr);
        default:
            return new ShowCommand(instruction, replyArr);
        }
    }

    /**
     * Returns the details of the command without the command type in a String.
     *
     * @param replyArr Array of String of the full command input by the user, split by " "
     * @return Returns a String of the details of the command
     */
    public static String stringifyDetails(String[] replyArr) {
        String details = "";
        for (int i = 1; i < replyArr.length; i++) {
            details += " " + replyArr[i];
        }
        return details;
    }


}
