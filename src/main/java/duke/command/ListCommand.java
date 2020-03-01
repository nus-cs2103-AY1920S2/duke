package duke.command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Lists all the tasks within Duke.
 */
public class ListCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        // Just display task list
        ui.showList(tasks);
        return tasks; // Don't update anything
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
