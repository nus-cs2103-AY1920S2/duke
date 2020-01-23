import java.util.ArrayList;

public class ExitCommand implements Command {
    @Override
    public void execute(ArrayList<Task> tasks) {
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
