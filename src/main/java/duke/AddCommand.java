package duke;

class AddCommand implements Command {
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.add(toAdd);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
