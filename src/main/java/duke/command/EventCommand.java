package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.exception.InsufficientArgumentsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String[] eventArgs;

    /**
     * Initialises a new EventCommand object.
     * @param cmdArgs The arguments passed into the EventCommand object
     * @throws DukeException If an insufficient number of arguments is provided
     */
    public EventCommand(String[] cmdArgs) throws DukeException {
        if (!hasValidNumOfArgs(cmdArgs.length)) {
            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a EVENT cannot be empty!");
        } else {
            String[] eventArgs = cmdArgs[1].split(" /at ", 2);
            if (!hasValidNumOfArgs(eventArgs.length)) {
                throw new InsufficientArgumentsException("☹ OOPS!!! Missing event parameters!");
            } else {
                this.eventArgs = eventArgs;
            }
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask = new Event(eventArgs[0], LocalDate.parse(eventArgs[1]));
            assert newTask != null : "newTask should not be null.";
            tasks.addTask(newTask);
            Ui.displayAddTaskSuccessMsg(newTask, tasks.getNumberOfTasks());
            storage.storeTasks(tasks.getTasks());
        } catch (DateTimeParseException ex) {
            Ui.printMessage("☹ OOPS!!! Please enter a valid date: YYYY-MM-DD");
        }
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
