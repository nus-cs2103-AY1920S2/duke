import java.util.ArrayList;

public class AddCommand implements Command {
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        tasks.add(toAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println(toAdd);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
