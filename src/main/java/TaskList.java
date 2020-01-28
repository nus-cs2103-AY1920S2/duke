import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected List<Task> userTaskList;

    public TaskList(List<Task> listOfTask) {
        this.userTaskList = listOfTask;
    }

    public TaskList() {
        this.userTaskList = new ArrayList<>();
    }

    public int getSize() {
        return userTaskList.size();
    }

    public Task getTask(int taskNumber) {
        return userTaskList.get(taskNumber);
    }

    public List<Task> getListOfTask() {
        return userTaskList;
    }

    public void addTask(Task task) {
        userTaskList.add(task);
    }

    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        return userTaskList.remove(taskNumber);
    }

}
