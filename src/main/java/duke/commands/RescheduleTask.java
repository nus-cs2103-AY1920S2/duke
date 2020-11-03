package duke.commands;

import java.time.LocalDateTime;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.storage.Storage;
import duke.parsers.CommandParser;
import duke.exceptions.DukeException;

/**
 * Reschedules an existing Task.
 */
class RescheduleTask extends Command {

    public RescheduleTask(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Extrack the task and datetime information
        String[] args = split(arg, " ");
        Task task = getTask(tasks, args);
        String dateTimeInfo = getDateTimeInfo(args);

        reschedule(task, dateTimeInfo);

        save(storage, tasks);

        ui.showReply(String.format("I've rescheduled this task:\n  %s", task));
    }

    private Task getTask(TaskList tasks, String[] args) throws DukeException {
        // Check if task number is valid
        int taskNo;
        try {
            taskNo = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new DukeException("Usage for Deadline: reschedule [task number] [due date]\n"
            + "Usage for Event: reschedule [task number] [start time] to [end time]");
        }
        // Check if task number is in range
        try {
            return tasks.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number provided is out of bounds!");
        }
    }

    private String getDateTimeInfo(String[] args) throws DukeException {
        if (args.length < 2 || args[1].isEmpty()) {
            throw new DukeException("Usage for Deadline: reschedule [task number] [due date]\n"
                    + "Usage for Event: reschedule [task number] [start time] to [end time]");
        }
        return args[1];
    }

    private void reschedule(Task task, String dateTimeInfo) throws DukeException {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            LocalDateTime newDateTime = commandParser.parse(dateTimeInfo);
            deadline.reschedule(newDateTime);
        } else if (task instanceof Event) {
            String[] dateTimes = commandParser.splitByDelimiter(dateTimeInfo, "to");
            if (dateTimes.length < 2) {
                throw new DukeException("Usage for Event: reschedule [task number] [start time] to [end time]");
            }
            Event event = (Event) task;
            LocalDateTime newStart = commandParser.parse(dateTimes[0].strip());
            LocalDateTime newEnd = commandParser.parse(dateTimes[1].strip());
            event.reschedule(newStart, newEnd);
        } else {
            // If it is neither Deadline nor Event, it cannot be rescheduled
            throw new DukeException("This task cannot be rescheduled!");
        }
    }
}