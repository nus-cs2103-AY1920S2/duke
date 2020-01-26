package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

class CreateEvent implements Command, TaskCreation {
    public void execute(String arg, TaskList tasks, Ui ui) throws DukeException {
        // Perform parsing of arguments
        String[] args = arg.split("/at");
        if (args.length < 2) {
            throw new DukeException("Usage: event [task name] /at [start datetime] to [end datetime]");
        }
        String taskName = args[0].strip();
        String[] dateTimes = args[1].strip().split("to");
        if (taskName.length() == 0 || dateTimes.length < 2) {
            throw new DukeException("Usage: event [task name] /at [start datetime] to [end datetime]");
        }

        // Create parsed Event
        DateTimeParser dtp = new DateTimeParser();
        Task newTask = new Event(taskName, dtp.parse(dateTimes[0].strip()), dtp.parse(dateTimes[1].strip()));
        tasks.add(newTask);

        // Display reply
        ui.showReply(CreateTaskReply(newTask, tasks));
    }
}