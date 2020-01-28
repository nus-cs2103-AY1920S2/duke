package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The type Done command which makes the task done.
 */
public class DoneCommand extends Command {
    /**
     * Instantiates a new Done command.
     *
     * @param userInput the user input
     */
    public DoneCommand(String userInput) {
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
     */

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task finishedTask = taskList.getList().get(splitDoneString(" ", userInput, taskList, ui));
        finishedTask.setDone(true);
        ui.printDone(finishedTask);
    }

    // To split the string coming in from done
    // Returns the index of the string after the word
    private int splitDoneString(String regrexWanted, String userInput, TaskList taskList, Ui ui) throws DukeException {
        String[] splittedString = userInput.split(regrexWanted);
        Integer arrayIndex = Integer.valueOf(splittedString[1]);

        if(arrayIndex > taskList.sizeOfList()) {
            ui.invalidNumberException();
        }

        return arrayIndex-1;
    }
}
