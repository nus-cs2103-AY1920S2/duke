package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.util.List;

public class AddTodoCommand implements Command {
    private TaskList taskList;
    private Todo todo;

    /**
     * Creates an AddTodoCommand that adds a new Todo to the tasklist.
     * @param taskList List of all the tasks saved by the user.
     * @param details Description of todo.
     */
    public AddTodoCommand(TaskList taskList, List<String> details) {
        String description = details.remove(0);
        Todo todo = new Todo(description, details.toArray(new String[details.size()]));
        this.taskList = taskList;
        this.todo = todo;
    }

    @Override
    public String execute() {
        taskList.add(todo);
        return "Got it. I've added this todo:\n"
                + todo
                + "\nNow you have " + taskList.size() + " tasks on the list.";
    }
}
