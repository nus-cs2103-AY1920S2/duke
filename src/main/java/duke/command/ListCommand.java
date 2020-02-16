package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.util.Scanner;


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
     * @param storage  Deals with loading tasks from file.
     * @param ui       Deals with interactions with the user
     * @param taskList List containing all the tasks
     * @throws DukeException Main exception method I have created
     */

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            return ui.printList(taskList, storage);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
