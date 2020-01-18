package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.tasks.Deadline;

class CreateDeadline implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks) {
        String[] args = arg.split("/by");
        if (args.length < 2) {
            System.out.print(formatReply("Usage: deadline [task name] /by [time]"));
            return;
        }
        Task newTask = new Deadline(args[0].strip(), args[1].strip());
        tasks.add(newTask);
        System.out.print(formatReply(CreateTaskReply(newTask, tasks)));
    }
}