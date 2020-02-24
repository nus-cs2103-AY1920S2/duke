package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for listing the tasks currently in the TaskList.
 *
 * @author Firzan Armani
 */
public class ListCommand extends Command {
    /**
     * ListCommand constructor.
     */
    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
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
        return String.join("\n", temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}