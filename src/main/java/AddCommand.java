public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    protected void execute(Storage storage, TaskList taskList) {
        // To be implemented
    }

}
