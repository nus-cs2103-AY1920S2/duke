/**
 * Represent a "deadline" command give to TaskList.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(Deadline deadline) {
        super();
        this.command = "deadline";
        this.task = deadline;
    }
}