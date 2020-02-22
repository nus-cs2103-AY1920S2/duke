package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a FindCommand.
 * Used to execute the FindCommand.
 */
public class FindCommand implements Command {
    private static final String NO_MATCHING_TASKS_MESSAGE = "There are no matching tasks in your list.\n";

    /** String argument for the command. */
    private String arg = "";

    /**
     * Constructs a new FindCommand.
     *
     * @param arg argument for the find command.
     */
    public FindCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the find command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String relevantTasks = tasks.findTasksBySearchPhrase(arg);
        if (relevantTasks.equals("")) {
            ui.addMessage(NO_MATCHING_TASKS_MESSAGE);
        } else {
            ui.addMessage("Here are the matching tasks in your list:\n" + relevantTasks);
        }
    }
}
