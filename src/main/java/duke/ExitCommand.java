package duke;

/**
 * Exit Command class that inherits from Command.
 */
public class ExitCommand extends Command {

    public String execute(Ui ui, Storage storage, ContactList contacts) {
        return "";
    }
    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        return ui.printExit();
    }
}
