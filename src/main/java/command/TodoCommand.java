package command;

import exception.DuplicateTaskAelitaException;
import exception.InsufficientArgumentAelitaException;
import main.Message;
import main.Response;
import main.TaskList;
import task.Task;
import task.Todo;

/**
 * The Todo command.
 */
public class TodoCommand extends Command {

    private String[] descriptionTokens;
    private TaskList taskList;

    /**
     * Constructs a new instance of Todo command.
     *
     * @param descriptionTokens the tokenized description.
     * @param taskList          the task list to add the new todo task.
     */
    public TodoCommand(String[] descriptionTokens, TaskList taskList) {

        this.descriptionTokens = descriptionTokens;
        this.taskList = taskList;
    }

    @Override
    public Response execute() throws InsufficientArgumentAelitaException, DuplicateTaskAelitaException {

        checkSufficientTokens(descriptionTokens, "todo");

        assert descriptionTokens.length > 0 : "There should be a description";

        Task task = new Todo(reconstructDescription(descriptionTokens));
        taskList.add(task);

        return new Response(Message.ADD_TASK, task);
    }

}
