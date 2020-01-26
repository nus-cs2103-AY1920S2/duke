package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

class MarkTaskAsDone implements Command {
    public void execute(String arg, TaskList tasks, Ui ui) throws DukeException {
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

    }
}