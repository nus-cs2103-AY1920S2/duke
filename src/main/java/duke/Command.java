package duke;

import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Function for all execution commands.
     *
     * @param task     the task
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @return the string
     * @throws IOException   the io exception
     * @throws DukeException the duke exception
     */
    String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Function to check if duke will exit.
     *
     * @return the boolean
     */
    boolean isExit();

}
