package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Todo;
import exception.IllegalTextException;

import java.util.regex.PatternSyntaxException;

public class TodoCommand extends Command {

    private String taskDescription;

    public TodoCommand(String taskDesc) throws IllegalTextException {
        try {
            this.taskDescription = taskDesc.split(" ", 2)[1];
        } catch (PatternSyntaxException e) {
            throw new IllegalTextException("Todo command must have a valid description.");
        }
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) {
        Todo newTodo = new Todo(this.taskDescription);
        storage.addToTaskList(newTodo);
        String text = "";
        text += "    Got it. I've added this task:\n" + "      " + newTodo + "    Now you have "
                + storage.getTaskList().size() + " tasks in the list.";
        return text;
    }
}