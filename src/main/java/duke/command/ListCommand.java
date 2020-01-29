package duke.command;

import duke.exception.DukeException;
import duke.io.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Prints all tasks that are currently in the TaskList.
     *
     * @param taskList The TaskList to print tasks from.
     * @param ui The Ui used to print the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.size() <= 0) {
            ui.stylizedPrint("There are currently no tasks.");
            return;
        }

        String[] names = taskList.getNames();
        for (int i = 0; i < names.length; ++i) {
            names[i] = (i + 1) + ". " + names[i];
        }
        ui.stylizedPrint(names);
    }
}
