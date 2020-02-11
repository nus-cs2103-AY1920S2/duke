import java.io.IOException;

public abstract class Command {
    Command(){
    }

    abstract String execute(TaskList task, Storage storage) throws IOException;

    boolean isExit() {
        return false;
    }
}