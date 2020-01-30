import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    void delete(int taskNum) {
        taskList.remove(taskNum);
    }

    public Task deleteTask(int i) {
        return taskList.remove(i);
    }
}
