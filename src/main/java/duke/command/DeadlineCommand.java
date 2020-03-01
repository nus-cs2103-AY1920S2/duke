package duke.command;

import duke.task.Deadline;
import duke.exception.DukeException;
import duke.exception.InsufficientArgumentsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String[] deadlineArgs;

    /**
     * Initialises a new DeadlineCommand.
     * @param cmdArgs The command arguments passed to the DeadlineCommand object
     * @throws DukeException If an insufficient number of arguments is provided
     */
    public DeadlineCommand(String[] cmdArgs) throws DukeException {
        if (!hasValidNumOfArgs(cmdArgs.length)) {
            throw new InsufficientArgumentsException("OOPS!!! The description of a DEADLINE cannot be empty!");
        } else {
            String[] deadlineArgs = cmdArgs[1].split(" /by ", 2);
            if (!hasValidNumOfArgs(deadlineArgs.length)) {
                throw new InsufficientArgumentsException("OOPS!!! Missing deadline parameters!");
            } else {
                this.deadlineArgs = deadlineArgs;
            }
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask = new Deadline(deadlineArgs[0], LocalDate.parse(deadlineArgs[1]));
            assert newTask != null : "newTask should not be null.";
            tasks.addTask(newTask);
            ui.displayAddTaskSuccessMsg(newTask, tasks.getNumberOfTasks());
            storage.storeTasks(tasks.getTasks());
        } catch (DateTimeParseException ex) {
            Ui.printMessage("OOPS!!! Please enter a valid date: YYYY-MM-DD");
        }
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
