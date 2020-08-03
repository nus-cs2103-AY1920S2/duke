package ip.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int i) {
        return tasks.remove(i);
    }

    public TaskList() {}

    public TaskList(List<Task> list) {
        this.tasks = (ArrayList<Task>) list;
    }

    public Task markTaskDone(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
        return t;
    }

    /**
     * Returns the number of tasks in the list
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a task at an index in the list
     * @param i the index of the task
     * @return the task at index i
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    public TaskList find(String keyword) {
        List<Task> found = tasks.stream()
                        .filter(t -> t.contains(keyword))
                        .collect(Collectors.toList());
        return new TaskList(found);
    }
}
