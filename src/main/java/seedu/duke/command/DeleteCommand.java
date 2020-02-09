package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidTaskInputException;
import seedu.duke.exception.TaskIndexOutOfBoundsException;

import java.io.IOException;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private String[] inputs;

    /**
     * Represents a DeleteCommand object.
     *
     * @param inputs The user input.
     */
    public DeleteCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Deletes the task from the list.
     *
     * @param taskList The TaskList object.
     * @param ui The User Interface object.
     * @param storage The hard disk object.
     * @throws EmptyDescriptionException If the description of a task is empty.
     * @throws InvalidTaskInputException If an invalid task command is input.
     * @throws IOException If an input or output exception occurred.
     * @throws TaskIndexOutOfBoundsException If the index of a task being marked as done or being deleted is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyDescriptionException,
            InvalidTaskInputException, IOException, TaskIndexOutOfBoundsException {
        if (inputs.length == 1) {
            throw new EmptyDescriptionException();
        }
        if (!isNumeric(inputs[1])) {
            throw new InvalidTaskInputException();
        }
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > taskList.getTasks().size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        taskList.deleteTask(index);
    }

    /**
     * Checks if a string can be converted to an integer.
     *
     * @param strNum The string to be checked.
     * @return true if the string can be converted to an integer.
     */
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int intNum = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }
}
