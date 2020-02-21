package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TimedTask;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.storage.Storage;
import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

/**
 * Reschedules an existing Task.
 */
class RescheduleTask extends TimedCommand {
    private final int NOT_FOUND = -1;

    public RescheduleTask(DateTimeParser dtParser) {
        super(dtParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Split string into taskNo and datetimes
        int taskNo;
        String dateTimeInfo;
        int spaceIndex = arg.indexOf(" ");
        if (spaceIndex == NOT_FOUND) {
            // No spaces found, so must be invalid usage
            throw new DukeException("Usage for Deadline: reschedule [task number] [due date]\n"
                    + "Usage for Event: reschedule [task number] [start time] to [end time]");
        } else {
            // Split string into taskNo and rest of string
            try {
                taskNo = Integer.parseInt(arg.substring(0, spaceIndex));
            } catch (NumberFormatException e) {
                throw new DukeException("Task number provided is invalid!");
            }
            dateTimeInfo = arg.substring(spaceIndex + 1);
        }

        // Check if task number is in range
        Task task;
        try {
            task = tasks.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }

        // Check for valid task type
        if (!(task instanceof TimedTask)) {
            throw new DukeException("This task cannot be rescheduled!");
        }

        // Perform rescheduling
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            LocalDateTime newDateTime = dtParser.parse(dateTimeInfo);
            deadline.reschedule(newDateTime);
        } else if (task instanceof Event) {
            String[] dateTimes = dateTimeInfo.split("to");
            if (dateTimes.length < 2) {
                throw new DukeException("Usage for Event: reschedule [task number] [start time] to [end time]");
            }
            Event event = (Event) task;
            LocalDateTime newStart = dtParser.parse(dateTimes[0].trim());
            LocalDateTime newEnd = dtParser.parse(dateTimes[1].trim());
            event.reschedule(newStart, newEnd);
        }

        // Save changes to disk
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DukeException("Error when saving to disk!");
        }

        // Display reply
        ui.showReply(String.format("I've rescheduled this task:\n  %s", task));
    }
}