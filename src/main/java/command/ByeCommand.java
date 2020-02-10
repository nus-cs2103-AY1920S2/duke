package command;

import exception.IoAelitaException;
import main.Message;
import main.Response;
import main.Storage;
import main.TaskList;

/**
 * The Bye command.
 */
public class ByeCommand extends Command {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an new instance of Bye command.
     *
     * @param storage  the storage object to save the task list.
     * @param taskList the task list to be saved.
     */
    public ByeCommand(Storage storage, TaskList taskList) {

        this.storage = storage;
        this.taskList = taskList;
    }

    @Override
    public Response execute() throws IoAelitaException {

        storage.saveTasks(taskList);

        return new Response(Message.GOODBYE, null);
    }

}
