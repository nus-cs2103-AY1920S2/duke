package duke.pack;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {

    }

    /**
     * Executes the list command and prints all tasks from TaskList
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    /**
     * indicates whether command is exit
     * @return boolean true if it is an exit command, else false
     */
    public Boolean isExit() {
        return false;
    }

}
