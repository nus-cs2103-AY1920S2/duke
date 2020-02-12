package command;

import exception.InsufficientArgumentAelitaException;
import exception.InvalidArgumentAelitaException;
import exception.InvalidListItemAelitaException;
import main.Message;
import main.Response;
import main.TaskList;

/**
 * The Delete command.
 */
public class DeleteCommand extends Command {

    private String[] commandTokens;
    private TaskList taskList;

    /**
     * Constructs a new instance of Delete command.
     *
     * @param commandTokens the tokenized command.
     * @param taskList      the task list to delete from.
     */
    public DeleteCommand(String[] commandTokens, TaskList taskList) {

        this.commandTokens = commandTokens;
        this.taskList = taskList;
    }

    @Override
    public Response execute() throws InsufficientArgumentAelitaException, InvalidArgumentAelitaException,
            InvalidListItemAelitaException {

        checkSufficientTokens(commandTokens, "delete");

        assert commandTokens.length > 1 : "There should be an index";

        int index = Integer.parseInt(commandTokens[1]) - 1;
        return new Response(Message.DELETE, taskList.remove(index));
    }

}
