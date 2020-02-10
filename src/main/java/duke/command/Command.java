package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Command interface to handle each user command.
 */
public interface Command {
    void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList);
}
