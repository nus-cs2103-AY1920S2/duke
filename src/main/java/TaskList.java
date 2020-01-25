import java.util.List;
import java.util.ArrayList;

public class TaskList {
    public List<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> existingTasks) {
        this.tasks = existingTasks;
    }

    public Task getTask(int num){
        return tasks.get(num);
    }

    public void addTodo(String name){
        this.tasks.add(new Todo(name));
    }

    public void addEvent(String name, String dateTime) {
        this.tasks.add(new Event(name, dateTime));
    }

    public void addDeadline(String name, String date) {
        this.tasks.add(new Deadline(name, date));
    }

    public void removeTask(int num){
        this.tasks.remove(num);
    }

    public List<Task> showList(){
        return this.tasks;
    }

    public void checkDone(int num){
        this.tasks.set(num, tasks.get(num).complete());
    }

    public int size() {
        return this.tasks.size();
    }
}