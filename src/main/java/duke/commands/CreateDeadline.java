package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

class CreateDeadline implements Command, TaskCreation {
    public void execute(String arg, TaskList tasks, Ui ui) throws DukeException {
        String[] args = arg.split("/by");
        if (args.length < 2) {
            throw new DukeException("Usage: deadline [task name] /by [datetime]");
        }
        String taskName = args[0].strip();
        String dateTime = args[1].strip();
        if (taskName.length() == 0 || dateTime.length() == 0) {
            throw new DukeException("Usage: deadline [task name] /by [datetime]");
        }
        DateTimeParser dtp = new DateTimeParser();
        Task newTask = new Deadline(taskName, dtp.parse(dateTime));
        tasks.add(newTask);
        ui.showReply(CreateTaskReply(newTask, tasks));
    }
}