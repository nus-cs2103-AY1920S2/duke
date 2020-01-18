package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.exceptions.DukeException;

class CreateTodo implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks) throws DukeException {
        if (arg.length() == 0) {
            throw new DukeException("Todo description cannot be empty!");
        }
        Task newTask = new Todo(arg);
        tasks.add(newTask);
        System.out.print(formatReply(CreateTaskReply(newTask, tasks)));
    }
}