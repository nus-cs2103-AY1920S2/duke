public class AddCommand implements Command {
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toAdd);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
