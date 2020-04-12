/**
 * Represent a "done" command given to TaskList.
 */
public class DoneCommand extends Command {

    public DoneCommand(int index) {
        super();
        this.command = "done";
        this.index = index;
    }
}
