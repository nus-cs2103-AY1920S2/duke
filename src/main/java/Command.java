public abstract class Command {

    protected boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

}
