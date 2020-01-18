package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.exceptions.DukeException;

class DeleteTask implements Command {
    public void execute(String arg, List<Task> tasks)  throws DukeException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage: delete [int]");
        }
        try {
            Task removed = tasks.remove(taskNo - 1);
            System.out.print(formatReply("Noted. I've removed this task:\n  " + removed));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
    }
}