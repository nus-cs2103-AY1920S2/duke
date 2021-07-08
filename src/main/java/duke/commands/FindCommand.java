package duke.commands;

import duke.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword a keyword supplied the user
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for tasks from taskList with descriptions containing the FindCommand object's keyword
     * Passes these tasks to Ui for printing.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return String find command message
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) { // TODO change implementation to streams
        ArrayList<Task> lst = taskList.getTaskList();
        ArrayList<Task> found = lst.stream()
                .filter(task -> task.getDescription().contains(this.keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return ui.findMsg(found, this.keyword);
    }
}
