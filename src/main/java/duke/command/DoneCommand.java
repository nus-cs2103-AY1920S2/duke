package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Mark a task in the task list as done if specified index is valid, save the task list and display to user.
     *
     * @param  tasks   the task list
     * @param   storage the storage object to save the list
     * @param   ui  the ui object to interact with user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputTokens = this.command.split(" ");

        // Mark the task with given index as done
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(inputTokens[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! No such task index!");
        }
        if (doneIndex < tasks.size()) {
            Task task = tasks.get(doneIndex);
            task.markAsDone(true);
            if (!storage.save(tasks)) {
                throw new DukeException("OOPS!!! Failed to save list!");
            }
            ui.showLine("Nice! I've marked this task as done: \n"
                    + "       " + task);
        } else {
            throw new DukeException("OOPS!!! No such task index!");
        }
    }
}
