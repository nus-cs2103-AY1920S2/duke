/**
 * ExitCommand inherits from Command and is called to terminate the program when user inputs "bye".
 */
public class ExitCommand extends Command {

    /**
     * Prints a goodbye message and terminates Duke.
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }
}
