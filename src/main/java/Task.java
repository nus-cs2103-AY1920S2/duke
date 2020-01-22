import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> tasks = new ArrayList<>();
    private String todo;
    private Boolean done = false;

    Task(String todo) {
        this.todo = todo;
    }

    public void done() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return "[✓] " + this.todo;
        } else {
            return "[✗] " + this.todo;
        }
    }

}
