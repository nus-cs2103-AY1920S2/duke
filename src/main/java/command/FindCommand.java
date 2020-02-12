package command;

import exception.InsufficientArgumentAelitaException;
import main.Message;
import main.Response;
import main.TaskList;

/**
 * The Find command.
 */
public class FindCommand extends Command {

    private String[] commandTokens;
    private TaskList taskList;

    /**
     * Constructs a new instance of Find command.
     *
     * @param commandTokens the tokenized command
     * @param taskList      the task list to search in.
     */
    public FindCommand(String[] commandTokens, TaskList taskList) {

        this.commandTokens = commandTokens;
        this.taskList = taskList;
    }

    @Override
    public Response execute() throws InsufficientArgumentAelitaException {

        checkSufficientTokens(commandTokens, "find");

        assert commandTokens.length > 1 : "There should be a keyword";

        String keyword = commandTokens[1].toLowerCase();

        //Gets the list of task containing the keyword
        TaskList results = taskList.list(keyword);

        return checkResult(results);
    }

    /**
     * Checks the resulting task list for any tasks.
     *
     * @param results the task list in question.
     * @return task not found if task list is empty and task found if otherwise.
     */
    private Response checkResult(TaskList results) {

        if (results.getSize() > 0) {
            return new Response(Message.TASK_FOUND, results);
        } else {
            return new Response(Message.TASK_NOT_FOUND, null);
        }
    }

}
