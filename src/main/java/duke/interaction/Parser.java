package duke.interaction;

import duke.command.*;
import duke.task.Task;
import duke.util.DukeException;

import java.util.Scanner;

public class Parser {
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
                    return new DoneCommand(in.nextInt() - 1);
                case "todo":
                    return new AddCommand(in.nextLine().trim(), Task.TaskType.TODO);
                case "deadline":
                    return new AddCommand(in.nextLine(), Task.TaskType.DEADLINE);
                case "event":
                    return new AddCommand(in.nextLine(), Task.TaskType.EVENT);
                case "delete":
                    return new DeleteCommand(in.nextInt() - 1);
                case "date":
                    return new DateCommand(in.nextLine().trim());
                default:
                    if (in.hasNextLine())
                        in.nextLine();
                    throw new DukeException.InvalidCommand();
            }
        } catch (DukeException.InvalidCommand e) {
            Ui.ShowError(e);
            return null;
        }
    }
}
