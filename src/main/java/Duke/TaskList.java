package Duke;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> todo = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> todo) {
        this.todo = todo;
    }

    public int getTaskListSize() {
        return todo.size();
    }

    public Task getTask(int i) {
        return todo.get(i);
    }

    public void removeTask(int i){
        todo.remove(i);
    }

    public void addTask(Task t){
        todo.add(t);
    }

    public ArrayList<Task> getEntireList() {
        return todo;
    }
}
