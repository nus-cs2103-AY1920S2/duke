package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

public interface Command {

    void execute(String task, Ui ui, Storage storage, TaskList taskList);

}
