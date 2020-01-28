package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * The type Delete command which deletes from the list
 */
public class DeleteCommand extends Command {

    /**
     * Instantiates a new Delete command.
     *
     * @param user_input the user input
     */
    public DeleteCommand(String user_input) {
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
     * @throws IOException For any potential Input/Output exceptions from incorrect file
     */

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws IOException, DukeException {
        if(user_input.contains("delete")) {
            Task deleted_task = taskList.getList().get(split_done_string(" ", user_input,taskList,ui));
            System.out.println("The deleted task is " + deleted_task);
            taskList.remove_from_list(deleted_task);
            ui.printDelete(deleted_task, taskList);
        }
    }

    private int split_done_string(String regrex_wanted, String user_input, TaskList taskList
    , Ui ui) throws DukeException {
        String[] splitted_string = user_input.split(regrex_wanted);
        Integer array_index = Integer.valueOf(splitted_string[1]);

        if(array_index > taskList.size_of_list()) {
            ui.invalid_number_exception();
        }

        return array_index-1;
    }
}
