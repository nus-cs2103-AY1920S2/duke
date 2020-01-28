package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * The type Done command which makes the task done.
 */
public class DoneCommand extends Command {
    /**
     * Instantiates a new Done command.
     *
     * @param user_input the user input
     */
    public DoneCommand(String user_input) {
        super(user_input);
    }


    /**
     * Overwrites the execute method from Abstract class execute.
     *
     * Check against the user's input then pass it to its respective task class.
     *
     * @param storage Deals with loading tasks from file.
     * @param ui Deals with interactions with the user
     * @param taskList List containing all the tasks
     * @throws DukeException Main exception method I have created
     */

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task finished_task = taskList.getList().get(split_done_string(" ", user_input, taskList, ui));
        finished_task.setDone(true);
        ui.printDone(finished_task);
    }

    // To split the string coming in from done
    // Returns the index of the string after the word
    private int split_done_string(String regrex_wanted, String user_input, TaskList taskList, Ui ui) throws DukeException {
        String[] splitted_string = user_input.split(regrex_wanted);
        Integer array_index = Integer.valueOf(splitted_string[1]);

        if(array_index > taskList.size_of_list()) {
            ui.invalid_number_exception();
        }

        System.out.println(array_index-1);
        return array_index-1;
    }
}
