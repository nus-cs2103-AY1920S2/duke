public class DoneCommand extends Command {

    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.showMessages(new String[]{"Nice! I've marked this task as done:",
                " " + task.toString()});
    }
}
