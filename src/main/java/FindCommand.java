/**
 * Represent a "find" command given to TaskList.
 */
public class FindCommand extends Command {

    public FindCommand(String taskToSearch) {
        super();
        this.command = "find";
        this.taskToSearch = taskToSearch;
    }

}
