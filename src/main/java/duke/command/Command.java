package duke.command;

import duke.main.Ui;
import duke.utils.FileStorage;
import duke.utils.Storage;
import duke.utils.TaskList;

public interface Command {

    public void execute(String task, Ui ui, Storage storage, TaskList taskList);
}
