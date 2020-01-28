package duke.main;

import java.util.List;
import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    public List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> existingTasks) {
        this.tasks = existingTasks;
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }

    public void add(Task t){
        tasks.add(t);
    }

    public void setDone(int taskNo) {
        this.tasks.set(taskNo, tasks.get(taskNo).complete());
    }

    public void removeTask(int taskNo) {
        this.tasks.remove(taskNo);
    }

    public int size() {
        return this.tasks.size();
    }

}