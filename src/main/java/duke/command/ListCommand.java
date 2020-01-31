package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;


/**
 * The type List command to show the user the list of items.
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new List command.
     *
     * @param userInput the user input
     */
    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * Overwrites the execute method from Abstract class execute.
     * Check against the user's input then pass it to its respective task class.
     *
     * @param storage Deals with loading tasks from file.
     * @param ui Deals with interactions with the user
     * @param taskList List containing all the tasks
     * @throws DukeException Main exception method I have created
     */

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws  DukeException {
        try {
            ui.printList(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
