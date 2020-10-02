/**
 * HelpCommand is a sub-class of Command.
 * It handles the printing of the help list.
 */
public class HelpCommand extends Command {

    /**
     * This method simply prints out the available commands.
     *
     * @param tasks The task list.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printHelp();
    }

    public boolean isExit() {
        return false;
    }
}
