package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Delete the task from list at a specific index if index is valid, save the task list and display to user.
     *
     * @param  tasks   the task list
     * @param   storage the storage object to save the list
     * @param   ui  the ui object to interact with user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputTokens = this.command.split(" ");
        int deleteIndex;

        try {
            deleteIndex = Integer.parseInt(inputTokens[1]) - 1;
            Task deleteTask = tasks.delete(deleteIndex);
            ui.showDeleteTask(deleteTask, tasks.size());
            if (!storage.save(tasks)) {
                throw new DukeException("OOPS!!! Failed to save list!");
            }
        } catch (Exception e) {
            throw new DukeException("OOPS!!! No such task index!");
        }
    }
}