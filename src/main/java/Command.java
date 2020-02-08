import java.io.IOException;

public abstract class Command {

    String command;

    public Command(String command) {
        this.command = command;
    }

    abstract boolean isExit();

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException ;
}
