package duke.commands;

import java.io.IOException;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.storage.Storage;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

/**
 * Creates an <code>Event</code> Task and adds it to the TaskList.
 */
class CreateEvent implements Command {
    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

        // Save new Event to disk
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DukeException("Error when saving to disk!");
        }

        // Display reply
        ui.showReply(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask,
                tasks.size()));
    }
}