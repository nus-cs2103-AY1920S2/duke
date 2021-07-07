package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.exception.EmptyDescriptionException;

/**
 * Represents a command to find a task based on user input.
 */
public class FindCommand extends Command {
    private String[] inputs;

    /**
     * Represents a FindCommand object.
     *
     * @param inputs The user input.
     */
    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Finds a task from the list.
     *
     * @param taskList The TaskList object.
     * @param ui The User Interface object.
     * @param storage The hard disk object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            }
            String desc = inputs[1];
            taskList.findTask(desc);
        } catch (DukeException e) {
            ui.print(e.toString());
        }

    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }
}
