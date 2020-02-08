package seedu.duke.command;


import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {
    TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printList();
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }
}
