package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class List extends Command {

    public List() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListMessage(tasks);
    }

    public String getHelpFormat() {
        return "Type command 'list' in lower case to see your tasks!";
    }
}
