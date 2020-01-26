package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public class DoneCommand extends Command {

    int doneIndex;

    public DoneCommand(int index) {
        doneIndex = index;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (taskList.doneTask(doneIndex))
            storage.saveTaskListToFile(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
