import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> tasks = new ArrayList<>();
    private String todo;

    Task(String todo) {
        this.todo = todo;
    }
    @Override
    public String toString() {
        return todo;
    }

}
