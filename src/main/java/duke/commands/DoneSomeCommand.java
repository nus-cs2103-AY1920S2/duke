package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Command that marks a few tasks as done at the same time.
 */
public class DoneSomeCommand extends Command {
    private ArrayList<Integer> indexList;

    public DoneSomeCommand(ArrayList<Integer> indexList) {
        this.indexList = indexList;
    }

    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        taskList.doneSpecificTasks(indexList);
        storage.writeNew(taskList);
        return ui.printSpecificDoneTasks(taskList.doneSpecificTasks(indexList));
    }
}

