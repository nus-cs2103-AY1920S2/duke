package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
     * @throws IOException   For wrong input/output exceptions
     */

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException, IOException {
        int indexToBeMarkedDone = obtainIndexToBeDeleted(" ", userInput, taskList, ui,
                storage.getNumberOfTasks());
        String[] storageArrays = storage.getStoredItems().split(System.lineSeparator());
        ArrayList<String> storageElements = new ArrayList<>(Arrays.asList(storageArrays));

        // Now if we modifying from the file itself.
        String preModifiedString = storageElements.get(indexToBeMarkedDone - 1);
        storageElements.remove(preModifiedString);
        return ui.printDelete(storageElements, storage, preModifiedString);
    }

    private int obtainIndexToBeDeleted(String regrexWanted, String userInput, TaskList taskList,
                                       Ui ui, int numOfElementsInStorage) throws DukeException {

        String[] splittedString = userInput.split(regrexWanted);
        String stringValue = Arrays.stream(splittedString)
                .filter(x -> x.equals(splittedString[1]))
                .collect(Collectors.joining());
        int arrayIndex = Integer.parseInt(stringValue);
        int index = taskList.sizeOfList() + numOfElementsInStorage;
        if (arrayIndex > index) {
            ui.invalidNumberException();
        }
        return arrayIndex;
    }
}
