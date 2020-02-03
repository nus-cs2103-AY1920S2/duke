import java.io.IOException;

public abstract class Command {
    Command(){
    }

    abstract String execute(TaskList task, Ui ui, Storage storage) throws IOException;

    abstract boolean isExit();
}