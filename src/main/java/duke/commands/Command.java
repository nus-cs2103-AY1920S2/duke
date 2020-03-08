package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the user input.
 */
public class Command {
    /**
     * Returns whether command leads to closing of Duke.
     * @return true if close, false if not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns actions according to the command.
     * @param taskList Deals with tasks in Duke.
     * @param storage Deals with saving of tasks in file.
     * @param ui Deals with interactions with the user.
     * @return Duke's response.
     * @throws IOException when there is error in executing command.
     */
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        return "";
    }
}

