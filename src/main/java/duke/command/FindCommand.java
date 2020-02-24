package duke.command;

import java.io.IOException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;
/**
 * Represents the command of finding a task in the task list. A <code>FindCommand</code> object corresponds to the
 * command of displaying the current task list e.g., <code>"book"</code>
 */

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */

    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Displays the list of tasks that have the keyword.
     * @param tasks The current task list.
     * @param ui The user interface to be updated.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (tasks.findTasks(keyword).isEmpty()) {
            System.out.println("Sorry, no such keyword in the task list.");
        } else {
            ui.printList(tasks.findTasks(keyword));
        }
    }
}
