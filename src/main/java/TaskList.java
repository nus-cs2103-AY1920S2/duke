import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
        System.out.println("Task list created!");
    }

    public ArrayList<Task> getList() {
        return taskList;
    }
}
