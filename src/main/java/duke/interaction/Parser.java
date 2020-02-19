package duke.interaction;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.DoneCommand;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.UndoCommand;
import duke.task.Task;
import duke.util.DukeException;

import java.util.Scanner;

/**
 * Handles the applications parsing behaviour,
 * deciding what action to take depending on the user input/command.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class Parser {

    /**
     * Analyses a full command string and converts
     * to an executable Command object.
     *
     * @param fullCommand read from user input.
     * @return Command object parsed from a full command string
     */
    public static Command parse(String fullCommand) throws DukeException.InvalidCommand {
        Scanner in = new Scanner(fullCommand);
        String input = in.next();
        try {
            switch (input.toLowerCase()) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "done":
                return new DoneCommand((in.hasNextInt() ? in.nextInt() - 1 : -1));
            case "todo":
                return new AddCommand((in.hasNextLine() ? in.nextLine().trim() : ""), Task.TaskType.TODO);
            case "deadline":
                return new AddCommand((in.hasNextLine() ? in.nextLine().trim() : ""), Task.TaskType.DEADLINE);
            case "event":
                return new AddCommand((in.hasNextLine() ? in.nextLine().trim() : ""), Task.TaskType.EVENT);
            case "delete":
                return new DeleteCommand((in.hasNextInt() ? in.nextInt() - 1 : -1));
            case "date":
                return new DateCommand((in.hasNextLine() ? in.nextLine().trim() : ""));
            case "find":
                return new FindCommand((in.hasNextLine() ? in.nextLine().trim() : ""));
            case "help":
                return new HelpCommand();
            case "undo":
                return new UndoCommand();
            default:
                if (in.hasNextLine()) {
                    in.nextLine();
                }
                throw new DukeException.InvalidCommand();
            }
        } catch (DukeException.InvalidCommand e) {
            Ui.showError(e);
            throw e;
        }
    }
}
