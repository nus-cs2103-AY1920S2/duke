package duke.commands;

import java.time.LocalDateTime;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.storage.Storage;
import duke.parsers.CommandParser;
import duke.exceptions.DukeException;

/**
 * Creates an Event Task and adds it to the TaskList.
 */
class CreateEvent extends Command {

    public CreateEvent(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Obtain command arguments from argument string
        String[] args = split(arg, "/at");
        String taskName = getTaskName(args);
        LocalDateTime[] dateTimes = getDateTimes(args);

        Task newTask = new Event(taskName, dateTimes[0], dateTimes[1]);
        tasks.add(newTask);

        save(storage, tasks);

        ui.showReply(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask,
                tasks.size()));
    }

    private String getTaskName(String[] args) throws DukeException {
        String taskName = args[0].strip();
        if (args.length < 2 || taskName.length() == 0) {
            throw new DukeException("Usage: event [task name] /at [start datetime] to [end datetime]");
        }
        return taskName;
    }

    private LocalDateTime[] getDateTimes(String[] args) throws DukeException {
        String[] dateTimeStrings = split(args[1].strip(), "to");
        if (dateTimeStrings.length < 2) {
            throw new DukeException("Usage: event [task name] /at [start datetime] to [end datetime]");
        }
        LocalDateTime[] dateTimes = new LocalDateTime[2];
        for (int i = 0; i < 2; i++) {
            dateTimes[i] = commandParser.parse(dateTimeStrings[i].strip());
        }
        return dateTimes;
    }
}
