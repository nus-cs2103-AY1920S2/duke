import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * The Duke Program setup needed object to run the program and gets responses from Ui.
 *
 * @version 1.1
 * @since 5/2/2020
 */
public class Duke {

    Ui ui;
    boolean isExit;

    /**
     * Creates objects needed for the program and
     * calls relevant method to set up the objects.
     */
    public void setup() {

        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/DataFile.txt");

        taskList.addStorage(storage);
        storage.addTaskList(taskList);
        storage.loadFile();
        Ui ui = new Ui(storage, taskList);
        this.ui = ui;

        isExit = false;

    }

    /**
     * Gets response based on user input.
     *
     * @param input refers to user input.
     * @return response in string form.
     */
    String getResponse(String input) {

        if (input.equals("bye")) {
            isExit = true;
        }

        return ui.frontDesk(input);
    }

    public boolean getIsExit() {

        return isExit;
    }

}
