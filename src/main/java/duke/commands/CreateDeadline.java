package duke.commands;

import java.util.List;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

class CreateDeadline implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks) throws DukeException {
        String[] args = arg.split("/by");
        if (args.length < 2) {
            throw new DukeException("Usage: deadline [task name] /by [dd/MM/yyyy] [HH:mm:ssss]");
        }
        String taskName = args[0].strip();
        String dateTime = args[1].strip();
        if (taskName.length() == 0 || dateTime.length() == 0) {
            throw new DukeException("Usage: deadline [task name] /by [dd/MM/yyyy] [HH:mm:ssss]");
        }
        DateTimeParser dtp = new DateTimeParser();
        Task newTask = new Deadline(taskName, dtp.parse(dateTime));
        tasks.add(newTask);
        System.out.print(formatReply(CreateTaskReply(newTask, tasks)));
    }
}