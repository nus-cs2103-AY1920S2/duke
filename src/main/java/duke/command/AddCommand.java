package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Add a Deadline/ Event/ To-do task to the task list, save the list to disk and display to user.
     *
     * @param  tasks   the task list
     * @param   storage the storage object to save the list
     * @param   ui  the ui object to interact with user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputTokens = command.split(" ");
        Task task;
        switch (inputTokens[0]) {
        case "todo":
            // Add a To-do task
            task = tasks.makeNewTodoTask(command);
            tasks.add(task);
            break;
        case "deadline":
            // Add a Deadline task
            task = tasks.makeNewDeadlineTask(command);
            tasks.add(task);
            break;
        case "event":
            // Add an Event task
            task = tasks.makeNewEventTask(command);
            tasks.add(task);
            break;
        default:
            throw new DukeException("OOPS!!! No such task type!");
        }
        if (task == null) {
            throw new DukeException("OOPS!!! duke.task.Task could not be added!");
        }
        if (!storage.save(tasks)) {
            throw new DukeException("OOPS!!! Failed to save list!");
        }
        ui.showAddTask(task, tasks.size());
    }
}
