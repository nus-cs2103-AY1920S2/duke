package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ErrorCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.task.TaskList;
import seedu.duke.enums.TaskTypes;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.ui.Ui;

public class Parser {
    public Parser() {

    }

    /**
     * Displays, modifies, and finds the content of the list according to the user's command input.
     *
     * @param inputs The user input.
     * @param taskList The TaskList object.
     * @return The respective command.
     */
    public Command handleCommands(String[] inputs, TaskList taskList) {
        String command = inputs[0].trim();
        Command cmd = null;
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
            cmd = new ErrorCommand(e.toString());
        }
        return cmd;
    }
}
