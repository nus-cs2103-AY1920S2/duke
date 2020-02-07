package duke.commands;

import java.io.IOException;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

/**
 * Marks as a specific Task as completed.
 */
class MarkTaskAsDone implements Command {
    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if task number is valid
        int taskNo;
        try {
            taskNo = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage: done [int]");
        }
        
        // Mark task as done
        try {
            Task task = tasks.get(taskNo);
            task.markAsDone();
            ui.showReply("Nice! I've marked this task as done:\n  " + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }

        // Save changes to disk
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DukeException("Error when saving to disk!");
        }

    }
}