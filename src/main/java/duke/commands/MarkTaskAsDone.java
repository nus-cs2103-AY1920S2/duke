package duke.commands;

import java.util.List;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.exceptions.DukeException;

class MarkTaskAsDone implements Command {
    public void execute(String arg, List<Task> tasks, Ui ui)  throws DukeException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage: done [int]");
        }
        try {
            Task task = tasks.get(taskNo - 1);
            task.markAsDone();
            ui.showReply("Nice! I've marked this task as done:\n  " + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
        
    }
}