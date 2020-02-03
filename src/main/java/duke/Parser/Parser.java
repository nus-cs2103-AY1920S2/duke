package duke.Parser;

import duke.Ui;
import duke.command.*;
import duke.task.Task;

import java.util.Optional;

public class Parser {
    public static Optional<Command> parse(String input) throws Exception {
        String[] arr = input.split("\\s");
        int index;
        try {
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

            case "find":
                String keyword = arr[1];
                if (arr.length > 2) {
                    Ui.printError(new Exception("Sorry I can only handle one word at a time"));
                }
                return Optional.of(new FindCommand(keyword));

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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("A tad too few words, innit? ");
        }
        return Optional.empty();
    }
}
