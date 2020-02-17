/**
 * Represent a DeleteCommand given to TaskList.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(int index) {
        super();
        this.command = "delete";
        this.index = index;
    }
}