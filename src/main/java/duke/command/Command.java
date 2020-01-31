package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

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
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * abstract Execute method.
     *
     * @param storage Deals with loading tasks from file.
     * @param ui Deals with interactions with the user
     * @param tasklist List containing all the tasks
     * @throws DukeException Main exception method I have created
     * @throws IOException For any potential Input/Output exceptions from incorrect file
     */
    public abstract void execute(Storage storage, Ui ui, TaskList tasklist) throws IOException, DukeException;
}
