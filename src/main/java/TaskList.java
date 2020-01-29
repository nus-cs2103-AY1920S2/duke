
import test.Task;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskStorage;
    public TaskList(ArrayList<Task> ts){
        taskStorage = ts;
    }
    public TaskList(){
        taskStorage = new ArrayList<>();
    }
    public TaskList add(Task t){
        taskStorage.add(t);
        return this;
    }
    public Task getTask(int numb){
        return taskStorage.get(numb);
    }
    public TaskList removeTask(int numb){
        taskStorage.remove(numb);
        return this;
    }
}
