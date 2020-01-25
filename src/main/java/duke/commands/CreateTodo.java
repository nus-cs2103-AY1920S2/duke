package duke.commands;

import java.util.List;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.exceptions.DukeException;

class CreateTodo implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks, Ui ui) throws DukeException {
        if (arg.length() == 0) {
            throw new DukeException("Todo description cannot be empty!");
        }
        Task newTask = new Todo(arg);
        tasks.add(newTask);
        ui.showReply(CreateTaskReply(newTask, tasks));
    }
}