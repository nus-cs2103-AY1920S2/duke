package duke;

import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
