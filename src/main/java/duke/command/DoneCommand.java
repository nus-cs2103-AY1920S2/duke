package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a done command issued by the user.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructs the done command.
     *
     * @param input User's input to specify the task number.
     * @param size Number of tasks currently in the task list.
     * @throws DukeException Thrown when the task number is out of range or the input is not a valid integer.
     */
    public DoneCommand(String input, int size) throws DukeException {
        try {
            String[] inputs = input.trim().split(" ", 2);
            int taskNumber = Integer.parseInt(inputs[1].trim()) - 1;
            if (taskNumber >= size) {
                throw new IndexOutOfBoundsException();
            }
            this.taskNumber = taskNumber;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
        }
    }

    /**
     * Executes the command.
     *
     * @param storage Storage class for the command to read/write data.
     * @param tasks TaskList class for the command to modify the task list.
     * @return Response to be displayed to the user.
     * @throws DukeException Thrown when there is an error reading/writing file.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        tasks.get(taskNumber).markAsDone();
        assert tasks.get(taskNumber).isTaskDone() : "Done command error.";
        storage.writeToFile("DONE", taskNumber);
        return "Nice! I've marked this task as done:\n    " + tasks.get(taskNumber);
    }
}
