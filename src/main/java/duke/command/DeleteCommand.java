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
        String[] tasksFromStorages = storage.getStoredItems().split(System.lineSeparator());
        ArrayList<String> storageTasks = new ArrayList<>(Arrays.asList(tasksFromStorages));

        // Now if we modifying from the file itself.
        String preModifiedString = storageTasks.get(indexToBeMarkedDone - 1);
        preModifiedString = formatModifiedString(preModifiedString);
        storageTasks.remove(storageTasks.get(indexToBeMarkedDone - 1));

        return ui.printDelete(storageTasks, storage, preModifiedString);
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


    private String formatModifiedString(String s) {
        String returnedString = "";
        if (s.substring(6, 7).equals("1")) {
            returnedString = s.substring(0, 6) + getStatusIcon(true) + s.substring(7);
        } else {
            returnedString = s.substring(0, 6) + getStatusIcon(false) + s.substring(7);
        }
        return returnedString;
    }

}
