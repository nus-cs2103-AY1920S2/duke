import java.util.ArrayList;

public class ListCommand implements Command{
    @Override
    public void execute(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
