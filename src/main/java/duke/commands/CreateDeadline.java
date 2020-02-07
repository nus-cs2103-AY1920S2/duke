package duke.commands;

import java.io.IOException;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.storage.Storage;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

/**
 * Creates a <code>Deadline</code> Task and adds it to the TaskList.
 */
class CreateDeadline implements Command {
    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Perform parsing of arguments
        String[] args = arg.split("/by");
        if (args.length < 2) {
            throw new DukeException("Usage: deadline [task name] /by [datetime]");
        }
        String taskName = args[0].strip();
        String dateTime = args[1].strip();
        if (taskName.length() == 0 || dateTime.length() == 0) {
            throw new DukeException("Usage: deadline [task name] /by [datetime]");
        }

        // Create parsed Deadline
        DateTimeParser dtp = new DateTimeParser();
        Task newTask = new Deadline(taskName, dtp.parse(dateTime));
        tasks.add(newTask);

        // Save new Deadline to disk
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