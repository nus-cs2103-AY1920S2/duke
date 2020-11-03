package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.parsers.CommandParser;

/**
 * Marks as a specific Task as completed.
 */
class MarkTaskAsDone extends Command {

    public MarkTaskAsDone(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNo = getTaskNo(arg);

        Task doneTask = markAsDone(tasks, taskNo);

        ui.showReply("Nice! I've marked this task as done:\n  " + doneTask);

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

    private Task markAsDone(TaskList tasks, int taskNo) throws DukeException {
        try {
            Task task = tasks.get(taskNo);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
    }
}
