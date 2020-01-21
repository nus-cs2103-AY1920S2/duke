import java.util.ArrayList;
import java.util.List;

public class UserText {
    private List<Task> userInput;

    public UserText() {
        userInput = new ArrayList<>();
    }

    public void addInput(Task s) {
        this.userInput.add(s);
        System.out.println("Now you have " + userInput.size() + " tasks in the list.");
    }

    public void printInputs() {
        int count = 1;
        System.out.println("Here is your list");
        for (Task s : userInput) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    public Task getTask(int n) {
        return this.userInput.get(n-1);
    }

    public void markDone(int taskNo) {
        userInput.get(taskNo - 1).markAsDone();
        System.out.println("Nice! I marked this task as done");
    }
}
