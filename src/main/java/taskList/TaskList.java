package taskList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public void addTodo(Todo td) {

    }

    public void addEvent(Event e) {

    }

    public void addDeadline(Deadline d) {

    }

    public void delete(int i) {

    }

    public void done(int i){

    }

    public String list() {
        return "";
    }

    public void remove(int index) {
        tasks.remove(index);
    }
}
