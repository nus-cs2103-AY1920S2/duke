package duke.logic.commands;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printTaskList(ui);
    }
}
