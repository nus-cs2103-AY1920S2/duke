package seedu.duke.command;

import seedu.duke.*;

public class FindCommand extends Command {
    private String[] inputs;

    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyDescriptionException {
        if (inputs.length == 1) {
            throw new EmptyDescriptionException();
        }
        String desc = inputs[1];
        taskList.findTask(desc);
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }
}
