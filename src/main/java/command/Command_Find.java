package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;

/**
 * Specific command to find the task with specific keyword
 */
public class Command_Find extends Command{

    private String keyword;

    public Command_Find(String keyword){
        this.keyword=keyword;
    }

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.findTask(keyword));
    }
}
