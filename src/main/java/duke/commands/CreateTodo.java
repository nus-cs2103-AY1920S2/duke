package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.parsers.CommandParser;
import duke.exceptions.DukeException;

/**
 * Creates a Todo Task and adds it to the TaskList.
 */
class CreateTodo extends Command {

    public CreateTodo(CommandParser commandParser) {
        super(commandParser);
    }

    public void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskName = getTaskName(arg);

        Task newTask = new Todo(taskName);
        tasks.add(newTask);

        save(storage, tasks);

        ui.showReply(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask,
                tasks.size()));
    }

    private String getTaskName(String arg) throws DukeException {
        String taskName = arg.strip();
        if (arg.length() == 0) {
            throw new DukeException("Usage: todo [task name]");
        }
        return taskName;
    }
}
