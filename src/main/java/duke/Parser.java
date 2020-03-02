package duke;

// Wildcard import is used because all classes in the package are imported

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UndoCommand;
import duke.task.Task;

import java.util.Optional;

/**
 * The Parser class that parses user inputs.
 */
public class Parser {
    /**
     * Parses a string input and returns a corresponding Command object.
     *
     * @param input the user input with type String.
     * @return a Command object.
     */
    public static Optional<Command> parse(String input) throws Exception {
        String[] arr = input.split("\\s");
        int index;
        try {
            switch (arr[0].toLowerCase()) {
            case "bye":
                if (arr.length > 1) {
                    Controller.raiseException(new Exception(" A word of bye is enough."));
                } else {
                    return Optional.of(new ExitCommand());
                }
                break;
            case "delete":
                index = Integer.parseInt(arr[1]) - 1;
                if (arr.length > 2) {
                    Controller.raiseException(new Exception(" More content than needed for delete task."));
                } else {
                    return Optional.of(new DeleteCommand(index));
                }
                break;
            case "done":
                index = Integer.parseInt(arr[1]) - 1;
                if (arr.length > 2) {
                    Controller.raiseException(new Exception(" More content than needed for done task."));
                } else {
                    return Optional.of(new DoneCommand(index));
                }
                break;
            case "find":
                String keyword = arr[1];
                if (arr.length > 2) {
                    Controller.raiseException(new Exception(" Sorry I can only handle one word at a time."));
                } else {
                    return Optional.of(new FindCommand(keyword));
                }
                break;
            case "list":
                if (arr.length > 1) {
                    Controller.raiseException(new Exception(" A word of list is enough."));
                } else {
                    return Optional.of(new ListCommand());
                }
                break;
            case "undo":
                if (arr.length > 1) {
                    Controller.raiseException(new Exception(" Too wordy for me."));
                } else {
                    return Optional.of(new UndoCommand());
                }
                break;
            default:
                try {
                    return Optional.of(new AddCommand(Task.generateTask(arr)));
                } catch (Exception e) {
                    Controller.raiseException(e);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception(" A tad too few words, innit? ");
        }
        return Optional.empty();
    }
}
