import java.util.ArrayList;

public interface Command {
    public void execute(ArrayList<Task> tasks);
    public boolean isExit();
}
