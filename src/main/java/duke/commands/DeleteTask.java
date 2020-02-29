package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.parsers.CommandParser;
import duke.exceptions.DukeException;

/**
 * Deletes a Task from the TaskList.
 */
class DeleteTask extends Command {

    public DeleteTask(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNo = getTaskNo(arg);

        Task removed = removeTask(tasks, taskNo);

        ui.showReply(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", removed,
                tasks.size()));

        save(storage, tasks);
    }

    private int getTaskNo(String arg) throws DukeException {
        // Check if task number is valid
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage: delete [int]");
        }
    }

    private Task removeTask(TaskList tasks, int taskNo) throws DukeException {
        try {
            return tasks.remove(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
    }
}
