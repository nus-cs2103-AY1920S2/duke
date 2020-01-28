package duke.command;

import duke.*;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Class which consists a command to terminate the program
 * When the user types "bye"
 */
public class ByeCommand extends Command {

    /**
     * Instantiates a new Bye command.
     *
     * @param user_input the user input
     */
    public ByeCommand(String user_input) {
        super(user_input);
    }


    /**
     * Overwrites the execute method from Abstract class execute.
     *
     * Check against the user's input then pass it UI class to print the Bye Message
     *
     * @param storage  Deals with loading tasks from file.
     * @param ui       Deals with interactions with the user
     * @param taskList List containing all the tasks
     * @throws DukeException Main exception method I have created
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {

        try {
            ui.printBye();
            taskList.getList().clear();
            Duke.break_checker = true;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
