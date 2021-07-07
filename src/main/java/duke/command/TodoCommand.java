package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Todo;

/**
 * Represents a todo command issued by the user.
 */
public class TodoCommand extends Command {
    private Todo todo;
    private String description;

    /**
     * Constructs the todo command.
     *
     * @param input User's input to specify the description of the task.
     * @throws DukeException Thrown when user did not specify a description.
     */
    public TodoCommand(String input) throws DukeException {
        try {
            String[] inputs = input.trim().split(" ", 2);
            this.description = inputs[1];
            todo = new Todo(description);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Executes the command.
     *
     * @param storage Storage class for the command to write data.
     * @param tasks TaskList class for the command to insert the task.
     * @return Response to be displayed to the user.
     * @throws DukeException Thrown when there is an error writing file.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException  {
        int size = tasks.size();
        tasks.add(todo);
        assert (size + 1) == tasks.size() : "Todo command error";
        storage.writeToFile("T | 0 | " + description + "\n", true);
        return "Got it. I've added this task:\n    " + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
