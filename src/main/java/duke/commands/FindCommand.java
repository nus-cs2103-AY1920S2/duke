package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Command that finds tasks consisting of given keyword.
 */
public class FindCommand extends Command {
    protected String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        return ui.printMatchList(taskList.findMatching(key));
    }
}

