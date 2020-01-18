package duke.commands;

import java.util.List;
import duke.tasks.Task;
import duke.tasks.Todo;

class CreateTodo implements Command, TaskCreation {
    public void execute(String arg, List<Task> tasks) {
        Task newTask = new Todo(arg);
        tasks.add(newTask);
        System.out.print(formatReply(CreateTaskReply(newTask, tasks)));
    }
}