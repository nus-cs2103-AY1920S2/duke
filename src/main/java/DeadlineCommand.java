public class DeadlineCommand extends Command {

    public DeadlineCommand(Deadline d) {
        super();
        this.command = "deadline";
        this.task = d;
    }

}