package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Command that lists all the tasks.
 */
public class ListCommand extends Command {
    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        return ui.printList(taskList);
    }
}

