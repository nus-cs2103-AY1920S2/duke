package duke;

/**
 * Exit Command class that inherits from Command.
 */
public class ExitCommand extends Command {

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printExit();
        System.exit(0);
    }
}
