package duke;

import duke.command.*;
import duke.task.Task;

import java.util.Optional;

/**
 * The Parser class that parses user inputs
 */
public class Parser {
    /**
     * Parses a string input and returns a corresponding Command object.
     *
     * @param input the user input with type String.
     * @return a Command object.
     */
    public static Optional<Command> parse(String input) {
        String[] arr = input.split("\\s");
        int index;

        switch (arr[0].toLowerCase()) {
        case "bye":
            if (arr.length > 1) {
                Ui.printError(new Exception("A word of bye is enough"));
            }
            return Optional.of(new ExitCommand());

        case "delete":
            index = Integer.parseInt(arr[1]) - 1;
            if (arr.length > 2) {
                Ui.printError(new Exception("More content than needed for delete task"));
            }
            return Optional.of(new DeleteCommand(index));

        case "done":
            index = Integer.parseInt(arr[1]) - 1;
            if (arr.length > 2) {
                Ui.printError(new Exception("More content than needed for done task"));
            }
            return Optional.of(new DoneCommand(index));

        case "list":
            if (arr.length > 1) {
                Ui.printError(new Exception("A word of list is enough"));
            }
            return Optional.of(new ListCommand());

        default:
            try {
                return Optional.of(new AddCommand(Task.generateTask(arr)));
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
        return Optional.empty();
    }
}
