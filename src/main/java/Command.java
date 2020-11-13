
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();

}
