package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] temp;
        if (tasks.getSize() == 0) {
            temp = new String[]{"Boss, my notepad is empty. You sure you told me anything?"};
        } else {
            temp = new String[tasks.getTaskList().size() + 1];
            temp[0] = "Here's what I've written down, boss.";
            for (int i = 0; i < tasks.getSize(); i++) {
                temp[i + 1] = (i + 1) + ". " + tasks.getTaskAt(i).toString();
            }
        }
        ui.dukePrompt(temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}