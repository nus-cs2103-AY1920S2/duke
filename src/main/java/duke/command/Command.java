package duke.command;

import duke.exception.DukeException;
import duke.exception.FindException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The abstract class Command handles all forms of Commands
 * AddCommand, ByeCommand, DeleteCommand, DoneCommand, ListCommand.
 */
public abstract class Command {

    /**
     * The User input.
     */
    String userInput;

    /**
     * Instantiates a new Command.
     *
     * @param userInput the user input
     */
    Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * abstract Execute method.
     *
     * @param storage  Deals with loading tasks from file.
     * @param ui       Deals with interactions with the user
     * @param tasklist List containing all the tasks
     * @throws DukeException Main exception method I have created
     * @throws IOException   For any potential Input/Output exceptions from incorrect file
     */
    public abstract String execute(Storage storage, Ui ui, TaskList tasklist)
            throws DukeException, FindException, IOException;



    protected String getStatusIcon(boolean isDone) {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}
