package seedu.duke.command;

import seedu.duke.*;

import java.io.IOException;

public class DoneCommand extends Command {
    private String[] inputs;

    public DoneCommand(String[] inputs) {
        this.inputs = inputs;
    }


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
        taskList.markTaskAsDone(index);
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }

    /**
     * Checks if a string can be converted to an integer.
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
}
