package duke.command;

import duke.exception.DukeException;
import duke.io.Ui;
import duke.task.TaskList;

import java.util.List;

public class FindCommand extends Command {

    protected String searchQuery;

    /**
     * Constructor for a Find Command.
     *
     * @param searchQuery The search query to use to find Tasks.
     */
    public FindCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Searches the input taskList for entries that match the input search query.
     * All matching tasks will be printed.
     *
     * @param taskList The TaskList to search.
     * @param ui The Ui used to print results.
     * @throws DukeException If the search query is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        DukeException.throwIf(searchQuery.equals(""), "The search term cannot be empty!");
        ui.printHorizontalBar();
        System.out.println("\tHere are the tasks matching your search query '" + searchQuery + "':");
        List<String> names = taskList.findTasks(this.searchQuery);
        for (int i = 0; i < names.size(); ++i) {
            System.out.println(String.format("\t%d. " + names.get(i), i + 1));
        }
        ui.printHorizontalBar();
    }
}
