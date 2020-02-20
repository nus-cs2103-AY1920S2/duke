package duke.logic.commands;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String commandWord, String keyword) {
        super(commandWord);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTask(ui, this.keyword);
    }
}
