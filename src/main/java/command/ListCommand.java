package command;

import exception.DukeException;
import io.Ui;
import task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
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
