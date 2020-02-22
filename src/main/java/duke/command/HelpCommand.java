package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a HelpCommand.
 * Used to execute the help command.
 */
public class HelpCommand implements Command {
    private static final String HELP_MESSAGE  = "Here are the list of commands I can perform:\n";
    private static final String BYE_INFO = "'bye' - Exits the application.\n";
    private static final String LIST_INFO = "'list' - Displays the list of tasks.\n";
    private static final String DONE_INFO = "'done <#index>' - Marks selected task with the given index as done.\n";
    private static final String DELETE_INFO = "'delete <#index>' - Deletes selected task with the given index.\n";
    private static final String TODO_INFO = "'todo <description>' - Adds a todo task with the given description.\n";
    private static final String DEADLINE_INFO = "'deadline <description> /by <dd/MM/yyyy> <hhmm>' -"
            + " Adds a deadline task with the given description that ends by the entered date and time.\n";
    private static final String EVENT_INFO = "'event <description> /at <dd/MM/yyyy> <hhmm>' -"
            + " Adds a event task with the given description that happens at the entered date and time.\n";
    private static final String FIND_INFO = "'find <search phrase>' - Displays the list of tasks containing the"
            + " given search phrase in the description.\n";
    private static final String REMINDER_INFO = "'reminder <type>' - Displays the list of tasks expiring in the"
            + " next 7 days. You must specify <type> to be 'all', 'deadline' or 'events'.\n";

    /**
     * Executes the help command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addMessage(HELP_MESSAGE + BYE_INFO + LIST_INFO + DONE_INFO + DELETE_INFO + TODO_INFO + DEADLINE_INFO
                + EVENT_INFO + FIND_INFO + REMINDER_INFO);
    }
}
