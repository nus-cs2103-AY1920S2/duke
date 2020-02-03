package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

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
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String relevantTasks = tasks.findTasksBySearchPhrase(arg);

        if (relevantTasks.equals("")) {
            return "There are no matching tasks in your list.";
        } else {
            return "Here are the matching tasks in your list:\n" + relevantTasks;
        }
    }
}
