package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an find command. Upon execution, produces some feedback to the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Create instance of FindCommand.
     *
     * @param keyword keyword to find
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = tasks.getTaskListWithKeyword(keyword);
        ui.showFindTask(result);
    }
}
