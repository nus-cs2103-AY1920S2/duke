package command;

import exception.DuplicateMarkAelitaException;
import exception.InsufficientArgumentAelitaException;
import exception.InvalidListItemAelitaException;
import main.Message;
import main.Response;
import main.TaskList;

/**
 * The Done command.
 */
public class DoneCommand extends Command {

    private String[] commandTokens;
    private TaskList taskList;

    /**
     * Instantiates a new Done command.
     *
     * @param commandTokens the tokenized command.
     * @param taskList      the task list which the task of interest is in.
     */
    public DoneCommand(String[] commandTokens, TaskList taskList) {

        this.commandTokens = commandTokens;
        this.taskList = taskList;
    }

    @Override
    public Response execute() throws InsufficientArgumentAelitaException, DuplicateMarkAelitaException,
            InvalidListItemAelitaException {

        checkSufficientTokens(commandTokens, "done");

        assert commandTokens.length > 1 : "There should be an index";

        int index = Integer.parseInt(commandTokens[1]) - 1;
        taskList.complete(index);
        return new Response(Message.DONE, index);
    }

}
