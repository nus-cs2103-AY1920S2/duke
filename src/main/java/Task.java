import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> tasks = new ArrayList<>();
    protected String task;
    protected Boolean done = false;

    Task(String todo) {
        this.task = todo;
    }

    public void done() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return "[✓] " + this.task;
        } else {
            return "[✗] " + this.task;
        }
    }

}
