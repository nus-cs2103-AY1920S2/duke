package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for listing the tasks currently in the TaskList.
 *
 * @author Firzan Armani
 */
public class ListCommand extends Command {
    private String filterString = "";

    /**
     * ListCommand constructor.
     */
    public ListCommand() {
        super();
    }

    /**
     * ListCommand constructor.
     *
     * @param filterString A string to filter the list (currently to be used to filter tagged tasks)
     */
    public ListCommand(String filterString) {
        super();
        this.filterString = filterString;
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
        String[] temp;
        ArrayList<Task> listedTaskList;
        if (this.filterString.isEmpty()) {
            listedTaskList = tasks.getTaskList();
        } else {
            listedTaskList = tasks.getTaggedTasks(filterString);
        }
        if (tasks.getSize() == 0) {
            temp = new String[]{"Boss, my notepad is empty. You sure you told me anything?"};
        } else {
            temp = new String[listedTaskList.size() + 1];
            temp[0] = "Here's what I've written down, boss.";
            for (int i = 0; i < listedTaskList.size(); i++) {
                temp[i + 1] = (i + 1) + ". " + listedTaskList.get(i).toString();
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