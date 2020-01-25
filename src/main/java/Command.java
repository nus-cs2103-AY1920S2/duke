public abstract class Command {
    String command;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
