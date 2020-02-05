package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * The ExitCommand program prints the exit message.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class ExitCommand extends Command {

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public ExitCommand(Storage storage, TaskList taskList) {

        super(storage, taskList);

    }

    /**
     * Prints out exit message.
     *
     * @param taskDescriptionArr is null.
     */
    @Override
    public String executeCommand(String[] taskDescriptionArr) {

        return "Bye. I hope you liked the service and hope to see you soon !";

    }
}
