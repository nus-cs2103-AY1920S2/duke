public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.removeTask(index);
        ui.showMessages(new String[]{"Noted. I've removed this task:", " " + task.toString(),
                "Now you have " + tasks.getSize() + " tasks in the list."});
    }
}
