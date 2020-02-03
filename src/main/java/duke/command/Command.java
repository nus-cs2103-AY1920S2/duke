package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Command interface to handle each user command
 */
public interface Command {

    void execute(String task, Ui ui, Storage storage, TaskList taskList);

}
