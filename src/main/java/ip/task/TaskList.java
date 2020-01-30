package ip.task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks = new ArrayList<>();
    public void add(Task t) {
        tasks.add(t);
    }
    public Task delete(int i) {
        return tasks.remove(i);
    }
    public Task markTaskDone(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
        return t;
    }
    public int size(){
        return tasks.size();
    }
    public Task get(int i){
        return tasks.get(i);
    }
}
