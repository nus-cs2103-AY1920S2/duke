package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidInputFormatException;
import seedu.duke.exception.TaskIndexOutOfBoundsException;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    private String[] inputs;

    /**
     * Represents a DoneCommand object.
     *
     * @param inputs The user input.
     */
    public DoneCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Marks a task as done.
     *
     * @param taskList The TaskList object.
     * @param ui The User Interface object.
     * @param storage The hard disk object.
     * @throws IOException If an input or output exception occurred.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        try {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            }

            if (!isNumeric(inputs[1])) {
                throw new InvalidInputFormatException();
            }
            int index = Integer.parseInt(inputs[1]);
            if (index < 1 || index > taskList.getTasks().size()) {
                throw new TaskIndexOutOfBoundsException();
            }
            taskList.markTaskAsDone(index);
        } catch (DukeException e) {
            ui.print(e.toString());
        }

    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }

    //@@author johannagwan-reused
    //Reused from https://www.baeldung.com/java-check-string-number with minor modifications.
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
    //@@author
}
