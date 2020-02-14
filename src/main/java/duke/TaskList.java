package duke;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public void doTask(int index) {
        this.tasks.get(index).doTask();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public Stream<Task> getTasksStream() {
        return tasks.stream();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
