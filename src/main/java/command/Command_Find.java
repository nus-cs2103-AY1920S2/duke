package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;

/**
 * Specific command to find task that has the keyword
 */
public class Command_Find extends Command{

    private String keyword;

    public Command_Find(String keyword){
        this.keyword=keyword;
    }

    /**
     * Display the list of task that has the keyword
     * @param common to check the task in the task list.
     * @param ui to display the response text of the matching tasks.
     * @throws DukeException when the task list is empty or
     * there is no matching task.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.findTask(keyword));
    }
}
