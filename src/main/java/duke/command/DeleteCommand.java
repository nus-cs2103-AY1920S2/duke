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
     * @param userInput the user input
     */
    public DeleteCommand(String userInput) {
        super(userInput);
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
        if(userInput.contains("delete")) {
            Task deletedTask = taskList.getList().get(splitDoneString(" ", userInput,taskList,ui));
            System.out.println("The deleted task is " + deletedTask);
            taskList.removeFromList(deletedTask);
            ui.printDelete(deletedTask, taskList);
        }
    }

    private int splitDoneString(String regrexWanted, String userInput, TaskList taskList
    , Ui ui) throws DukeException {
        String[] splittedString = userInput.split(regrexWanted);
        Integer arrayIndex = Integer.valueOf(splittedString[1]);

        if(arrayIndex > taskList.sizeOfList()) {
            ui.invalidNumberException();
        }

        return arrayIndex-1;
    }
}
