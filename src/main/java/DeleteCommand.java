import java.util.ArrayList;

public class DeleteCommand implements Command {
    private int deleteIndex;  //doneIndex is 0-indexed

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(deleteIndex));
        tasks.remove(deleteIndex);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
