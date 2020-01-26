package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

/**
 * Deletes a Task from the TaskList.
 */
class DeleteTask implements Command {
    public void execute(String arg, TaskList tasks, Ui ui) throws DukeException {
        // Check if task number is valid
        int taskNo;
        try {
            taskNo = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage: delete [int]");
        }

        // Delete task
        try {
            Task removed = tasks.remove(taskNo);
            String reply = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                    removed, tasks.size());
            ui.showReply(reply);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
    }
}