package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.tasks.Event;
import duke.exceptions.DukeException;

class CreateEvent implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks) throws DukeException {
        String[] args = arg.split("/at");
        if (args.length < 2) {
            throw new DukeException("Usage: event [task name] /at [time]");
        }
        String taskName = args[0].strip();
        String dateTime = args[1].strip();
        if (taskName.length() == 0) {
            throw new DukeException("Event description cannot be empty!");
        }
        if (dateTime.length() == 0) {
            throw new DukeException("Event date/time cannot be empty!");
        }
        Task newTask = new Event(taskName, dateTime);
        tasks.add(newTask);
        System.out.print(formatReply(CreateTaskReply(newTask, tasks)));
    }
}