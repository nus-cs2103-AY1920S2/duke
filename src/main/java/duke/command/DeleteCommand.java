package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The type Delete command which deletes from the list.
 */
public class DeleteCommand extends Command {

    /**
     * Instantiates a new Delete command.
     *
     * @param userInput the user input
     */
    public DeleteCommand(String userInput) {
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
        Task deletedTask = taskList.getList().get(obtainIndexToBeDeleted(" ", userInput, taskList, ui));
        taskList.removeFromList(deletedTask);
        assert (!taskList.getList().contains(deletedTask)) : "taskList should not contain the deleted task!";
        return ui.printDelete(deletedTask, taskList);

    }

    private int obtainIndexToBeDeleted(String regrexWanted, String userInput, TaskList taskList,
                                       Ui ui) throws DukeException {
        String[] splittedString = userInput.split(regrexWanted);
        Integer arrayIndex = Integer.valueOf(splittedString[1]);

        if (arrayIndex > taskList.sizeOfList()) {
            ui.invalidNumberException();
        }

        return arrayIndex - 1;
    }
}
