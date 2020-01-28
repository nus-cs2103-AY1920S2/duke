public abstract class Command {

    private Task task;

    public Command(Task task) { this.task = task; }

    public Command() {this.task = null; }

    public Task getTask() {
        return task;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();

}

