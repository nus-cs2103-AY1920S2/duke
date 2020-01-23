import java.util.ArrayList;

public class InvalidCommand implements Command{
    @Override
    public void execute(ArrayList<Task> tasks) {
        System.out.println("invalid command execute being called");
    }

    @Override
    public boolean isExit() {
        System.out.println("invalid command execute being called");
        return false;
    }
}
