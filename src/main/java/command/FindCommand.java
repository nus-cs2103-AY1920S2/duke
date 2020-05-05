package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command object for finding tasks related to a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a command object to display tasks
     * related to a keyword.
     * @param keyword The keyword to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to list out the related tasks.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filtered = tasks.filterKeyword(this.keyword);
        return Ui.showFindings(filtered);
    }
}
