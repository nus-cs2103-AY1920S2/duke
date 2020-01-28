public abstract class Command {

    public String command;
    public String description;
    public boolean isExit;

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
        isExit = false;
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
