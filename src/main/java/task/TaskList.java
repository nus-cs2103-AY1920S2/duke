package task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public void addNewTodo(String task) {
        tasks.add(new Todo(task));
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addNewDeadline(String task, LocalDateTime time) {
        tasks.add(new Deadline(task, time));
    }

    public void addNewEvent(String task, LocalDateTime time) {
        tasks.add(new Event(task, time));
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getCurrentTasks() {
        return this.tasks;
    }

    public void removeTask(Task targetedTask) {
        this.tasks.remove(targetedTask);
    }

}
