package duke.commands;

import java.util.List;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.exceptions.DukeException;

class DeleteTask implements Command {
    public void execute(String arg, List<Task> tasks, Ui ui) throws DukeException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage: delete [int]");
        }
        try {
            Task removed = tasks.remove(taskNo - 1);
            String reply = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                    removed, tasks.size());
            ui.showReply(reply);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
    }
}