package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Todo;

public class TodoCommand extends Command {

    private String taskDescription;

    public TodoCommand(String taskDesc) {
        this.taskDescription = taskDesc;
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        Todo newTodo = new Todo(this.taskDescription);
        storage.addToTaskList(newTodo);
        String text = "";
        text += "    Got it. I've added this task:\n" + "      " + newTodo + System.lineSeparator()
                + "    Now you have " + storage.getTaskList().size() + " tasks in the list.";
        return text;
    }
}