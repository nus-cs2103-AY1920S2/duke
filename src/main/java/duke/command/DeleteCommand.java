package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

/**
 * Represents a delete command issued by the user.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs the delete command.
     *
     * @param input User's input to specify the task number.
     * @param size Number of tasks currently in the task list.
     * @throws DukeException Thrown when the task number is out of range or the input is not a valid integer.
     */
    public DeleteCommand(String input, int size) throws DukeException {
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
        int size = tasks.size();
        Task deletedTask = tasks.remove(taskNumber);
        assert (size - 1) == tasks.size() : "Delete command error";
        storage.writeToFile("DELETE", taskNumber);
        return "Noted. I've removed this task: \n    " + deletedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
