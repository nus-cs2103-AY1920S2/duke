package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        this.command = command;
    }

    /**
     * Display good bye message to user.
     *
     * @param tasks the task list
     * @param storage the storage object to save the list
     * @param ui the ui object to interact with user
     * @return message to be displayed
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert command.split(" ")[0] == "bye";
        return ui.showGoodBye();
    }
}