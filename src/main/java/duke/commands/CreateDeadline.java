package duke.commands;

import java.time.LocalDateTime;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.storage.Storage;
import duke.parsers.CommandParser;
import duke.exceptions.DukeException;

/**
 * Creates a Deadline Task and adds it to the TaskList.
 */
class CreateDeadline extends Command {

    public CreateDeadline(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Obtain command arguments from argument string
        String[] args = split(arg, "/by");
        String taskName = getTaskName(args);
        LocalDateTime dateTime = getDateTime(args);

        Task newTask = new Deadline(taskName, dateTime);
        tasks.add(newTask);

        save(storage, tasks);

        ui.showReply(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask,
                tasks.size()));
    }

    private String getTaskName(String[] args) throws DukeException {
        String taskName = args[0].strip();
        if (args.length < 2 || taskName.length() == 0) {
            throw new DukeException("Usage: deadline [task name] /by [due date]");
        }
        return taskName;
    }

    private LocalDateTime getDateTime(String[] args) throws DukeException {
        return commandParser.parse(args[1].strip());
    }
}
