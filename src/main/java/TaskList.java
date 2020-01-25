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

    public String addTodo(String name){
        Task newT = new Todo(name);
        this.tasks.add(newT);
        return newT.toString();
    }

    public String addEvent(String name, String dateTime) {
        Task newE = new Event(name, dateTime);
        this.tasks.add(newE);
        return newE.toString();
    }

    public String addDeadline(String name, String date) {
        Task newD = new Deadline(name, date);
        this.tasks.add(newD);
        return newD.toString();
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