/**
 * Class to present invalid commands.
 */
public class InvalidCommand extends Command {

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public InvalidCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    /**
     * Execute the given task.
     * @param ui the Ui class in charge of aesthetics.
     * @param list tasklist that stores all the task.
     * @param storage storage that writes to file.
     * @throws DukeException exception.
     */
    @Override
    public String execute(UI ui, TaskList list, Storage storage, HistoryManager historyManager) throws DukeException {
        return ui.prettyPrinting("Invalid command! Please try again!");
    }
}
