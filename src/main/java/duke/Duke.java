package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UiText;
import duke.ui.Ui;

public class Duke implements Copyable {
    public Storage storage;
    public TaskList tasks;
    public UiText ui;

    /**
     * If no file path specified, default path is assumed
     */

    public Duke(UiText ui) {
        this(Storage.DEFAULT_PATH, ui);
    }

    /**
     * constructor to specify file path of the last saved data
     *
     * @param filePath = path of last saved data file
     */
    public Duke(String filePath, UiText ui) {
        this.ui = ui;
        this.storage = new Storage(filePath);
        if (this.storage.fileExist()) {
            try {
                this.tasks = TaskList.fromCSVList(storage.loadCSVList());
            } catch (Exception e) {
                this.ui.respond(Ui.loadingErrorMsg);
                this.tasks = new TaskList();
            }
        } else {
            try {
                this.storage.createFile();
            } catch(Exception e) {
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
    }

    private Duke(Storage storage, TaskList tasks, UiText ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    public Duke getCopy() {
        return new Duke(this.storage, this.tasks.getCopy(), this.ui);
    }
}
