import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * The Duke Program contains main method.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class Duke {

    Ui Ui ;

    public void setup() {

        TaskList taskList = new TaskList();
        Storage storage = new Storage("../../DataFile.txt");

        taskList.addStorage(storage);
        storage.addTaskList(taskList);
        storage.loadFile();
        Ui ui = new Ui(storage, taskList);
        this.Ui = ui;

    }

    String getResponse(String input) {
        return Ui.frontDesk(input);
    }

}
