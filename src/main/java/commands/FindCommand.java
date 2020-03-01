package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String taskSearchKey;

    /**
     * Constructs a FindCommand instance.
     *
     * @param taskAction is the task search keyword.
     */
    public FindCommand(String taskAction) {
        super();
        this.taskSearchKey = taskAction;
    }

    /**
     * Executes the find command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return found tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.acknowledgeFound(tasks, this.taskSearchKey);
    }
}
