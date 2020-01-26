package duke.interaction;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.DoneCommand;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.task.Task;
import duke.util.DukeException;

import java.util.Scanner;

public class Parser {

    /**
     * Analyses a full command string and converts
     * to an executable Command object.
     * @param fullCommand read from user input.
     * @return Command object parsed from a full command string
     */
    public static Command parse(String fullCommand) {
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
                    return new DateCommand(in.nextLine().trim());
                default:
                    if (in.hasNextLine())
                        in.nextLine();
                    throw new DukeException.InvalidCommand();
            }
        } catch (DukeException.InvalidCommand e) {
            Ui.showError(e);
            return null;
        }
    }
}
