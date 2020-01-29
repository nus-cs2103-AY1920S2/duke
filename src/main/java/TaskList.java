import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList(List<Task> list) {
        this.taskList = new ArrayList<>(list);
    }

    public TaskList(TaskList other) {
        if (other == null) {
            this.taskList = new ArrayList<>();
        } else {
            this.taskList = new ArrayList<>(other.taskList);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        removeAtIndex(taskList.indexOf(task));
    }

    public void removeAllTask() {
        taskList.clear();
    }

    public Task removeAtIndex(int index) {
        return taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }
}
