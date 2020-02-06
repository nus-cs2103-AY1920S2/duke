import java.io.IOException;

public abstract class Command {
    Command(){
    }

    abstract String execute(TaskList task, Storage storage) throws IOException;

    abstract boolean isExit();
}