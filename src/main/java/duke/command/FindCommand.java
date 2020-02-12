package duke.command;

import duke.History;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a find command.
 * The command deals with filtering the list of tasks to match keyword.
 */
public class FindCommand extends Command {

    protected String keyword;

    /**
     * Constructs a FindCommand with specified keyword.
     *
     * @param keyword The specified keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters the list of tasks that match keyword and returns relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of String.
     */
    @Override
    public String execute(TaskList tasks, History history) {
        ArrayList<Task> matchingList = tasks.find(keyword);
        return Ui.generateMatchingListMessage(matchingList);
    }
}
