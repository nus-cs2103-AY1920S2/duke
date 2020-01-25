package duke.commands;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

class CreateTodo implements Command, TaskCreation {
    public void execute(String arg, TaskList tasks, Ui ui) throws DukeException {
        if (arg.length() == 0) {
            throw new DukeException("Todo description cannot be empty!");
        }
        Task newTask = new Todo(arg);
        tasks.add(newTask);
        ui.showReply(CreateTaskReply(newTask, tasks));
    }
}