import java.io.IOException;

public abstract class Command {
    Command(){
    }

    abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;

    abstract boolean isExit();
}