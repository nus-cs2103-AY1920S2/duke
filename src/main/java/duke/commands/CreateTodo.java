package duke.commands;

import java.io.IOException;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

/**
 * Creates a Todo Task and adds it to the TaskList.
 */
class CreateTodo implements Command {
    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arg.length() == 0) {
            throw new DukeException("Usage: todo [task name]");
        }

        // Create new Task
        Task newTask = new Todo(arg);
        tasks.add(newTask);

        // Save new Todo to disk
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