package duke.command;

import duke.task.TaskList;
import duke.interaction.Ui;
import duke.util.Storage;

/**
 * Represents the Command for the "help" input by the user.
 * It displays all possible commands available.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class HelpCommand extends Command {

    /**
     * Executes List behaviour of showing all tasks in collection.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        Ui.showAllCommands(new AddCommand(), new ListCommand(), new DateCommand(), new DeleteCommand(),
                new DoneCommand(), new ExitCommand(), new FindCommand(), new UndoCommand(), this);
    }

    /**
     * Inform if command is an exit command.
     *
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "help - Shows all possible commands available.";
    }
}
