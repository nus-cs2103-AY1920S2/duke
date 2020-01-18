package duke.commands;

import java.util.List;
import duke.tasks.Task;

class MarkTaskAsDone implements Command {
    public void execute(String arg, List<Task> tasks) {
        int taskNo = Integer.parseInt(arg);
        Task task = tasks.get(taskNo - 1);
        task.markAsDone();
        System.out.print(formatReply("Nice! I've marked this task as done:\n  " + task));
    }
}