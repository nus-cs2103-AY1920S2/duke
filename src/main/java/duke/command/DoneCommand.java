package duke.command;


import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    private static final boolean iSDONE = true;

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
        int indexToBeMarkedDone = obtainIndexToBeMarkedDone(" ", userInput, taskList, ui,
                storage.getNumberOfTasks());
        String[] storageArrays = storage.getStoredItems().split(System.lineSeparator());
        ArrayList<String> storageElements = new ArrayList<>(Arrays.asList(storageArrays));
        // Now if we modifying from the file itself.
        String preModifiedString = storageElements.get(indexToBeMarkedDone - 1);
        storageElements.remove(preModifiedString);
        StringBuilder stringFromStorage = new StringBuilder(preModifiedString);
        // TODO Why cant you set an ascii code to a character?
        //stringFromStorage.setCharAt(6, '\u2713');
        String postModifiedString = stringFromStorage.substring(0,6) + "1"
                + stringFromStorage.substring(7);
        storageElements.add(indexToBeMarkedDone - 1, postModifiedString);
        return ui.printDone(storageElements, storage, postModifiedString);
    }

    // To split the string coming in from done
    // Returns the index of the string after the word
    private int obtainIndexToBeMarkedDone(String regrexWanted, String userInput,
                                          TaskList taskList, Ui ui,
                                          int numOfElementsInStorage) throws DukeException {
        String[] splittedString = userInput.split(regrexWanted);

        String indexAsString = Arrays.stream(splittedString)
                .filter(x -> x.equals(splittedString[1]))
                .collect(Collectors.joining());

        int arrayIndex = Integer.parseInt(indexAsString);
        int index = taskList.sizeOfList() + numOfElementsInStorage;


        if (arrayIndex > index) {
            ui.invalidNumberException();
        }

        return arrayIndex;
    }

    private String getStatusIcon() {
        return (iSDONE ? "\u2713" : "\u2718"); //return tick or X symbols
    }


}

