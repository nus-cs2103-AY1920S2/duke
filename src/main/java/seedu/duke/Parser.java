package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.enums.TaskTypes;

public class Parser {
    public Parser() {

    }

    /**
     * Displays, modifies, and finds the content of the list according to the user's command input.
     */
    public Command handleCommands(String[] inputs, TaskList taskList) {
        String command = inputs[0].trim();
        Command cmd = null;
        Ui ui = new Ui();
        try {
            switch (command) {
            case "list":
                cmd = new ListCommand(taskList);
                break;
            case "bye":
                cmd = new ByeCommand();
                break;
            case "todo":
                cmd = new AddCommand(TaskTypes.TODO, inputs);
                break;
            case "deadline":
                cmd = new AddCommand(TaskTypes.DEADLINE, inputs);
                break;
            case "event":
                cmd = new AddCommand(TaskTypes.EVENT, inputs);
                break;
            case "done":
                cmd = new DoneCommand(inputs);
                break;
            case "delete":
                cmd = new DeleteCommand(inputs);
                break;
            case "find":
                cmd = new FindCommand(inputs);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            ui.print(e.toString());
        }
        return cmd;
    }
}
