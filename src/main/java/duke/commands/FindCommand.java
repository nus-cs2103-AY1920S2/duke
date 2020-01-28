package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    protected String command;

    public FindCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Searches through existing list for matching keyword
     * @param tasks List of current tasks
     * @param ui User interface used to reply user
     * @param storage For storing of tasks into file
     * @throws DukeException If keyword is not in list of tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        if (command.equals("find")) {
            throw new DukeException("Please enter a keyword to search for.");
        }

        String keyword = command.split(" ")[1];
        TaskList matchingTasks = tasks.findKeyWord(ui, keyword);
        matchingTasks.printTaskList(ui);
    }
}


