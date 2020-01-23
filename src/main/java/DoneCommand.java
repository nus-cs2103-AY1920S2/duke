import java.util.ArrayList;

public class DoneCommand implements Command {
    private int doneIndex;  //doneIndex is 0-indexed

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        //doneIndex is 0-indexed
        tasks.get(doneIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(doneIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
