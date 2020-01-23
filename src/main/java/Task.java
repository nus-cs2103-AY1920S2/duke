import java.util.List;
import java.util.ArrayList;

public class Task {
    static List<Task> taskList = new ArrayList<>();
    String taskName;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public static String addTask(String taskName){
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        return "added: " + taskName;
    }
    @Override
    public String toString(){
        return this.taskName;
    }
}
