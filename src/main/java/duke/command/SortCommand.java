package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class SortCommand extends Command {
    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return tasklist.sortDeadline();
    }
}
