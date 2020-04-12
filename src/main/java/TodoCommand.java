/**
 * Represent a "todo" command given to TaskList.
 */
public class TodoCommand extends Command {

    public TodoCommand(Todo t) {
        super();
        this.command = "todo";
        this.task = t;
    }
}