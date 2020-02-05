/**
 * A command to add a task to Duke.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SavingException {
        tasks.addTask(this.task);
        storage.save(tasks.getTasks());
        String response = "Got it. I've added this task:\n" + this.task + '\n';
        response += "Now you have " + tasks.getNumTasks() + " tasks in the list.";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
