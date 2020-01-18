package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.tasks.Event;

class CreateEvent implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks) {
        String[] args = arg.split("/at");
        if (args.length < 2) {
            System.out.print(formatReply("Usage: event [task name] /at [time]"));
            return;
        }
        Task newTask = new Event(args[0].strip(), args[1].strip());
        tasks.add(newTask);
        System.out.print(formatReply(CreateTaskReply(newTask, tasks)));
    }
}