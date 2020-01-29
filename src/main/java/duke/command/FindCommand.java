package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a FindCommand.
 * Used to execute the FindCommand.
 */
public class FindCommand extends Command {
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

        ui.printDividerLine();
        if (relevantTasks.equals("")) {
            ui.printMessageNoDivider("     There are no matching tasks in your list.\n");
        } else {
            ui.printMessageNoDivider("     Here are the matching tasks in your list:\n");
            ui.printMessageNoDivider(relevantTasks);
        }
        ui.printDividerLine();
    }
}
