import java.util.ArrayList;
import java.util.List;

public class UserText {
    private List<Task> allTasks;

    public UserText() {
        allTasks = new ArrayList<>();
    }

    public void addInput(Task s) {
        this.allTasks.add(s);
        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
    }

    public void printInputs() {
        int count = 1;
        System.out.println("Here is your list");
        for (Task s : allTasks) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    public Task getTask(int n) {
        return this.allTasks.get(n-1);
    }

    public void markDone(int taskNo) {
        allTasks.get(taskNo - 1).markAsDone();
        System.out.println("Nice! I marked this task as done");
    }

    public void removeTask(int taskNo) {
        Task tempTask = allTasks.remove(taskNo - 1);
        System.out.println("Noted. I have removed this task");
        System.out.println(" " + tempTask);
        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
    }
}
