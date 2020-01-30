package duke.tasks;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public static TaskList doneTask(int i, TaskList taskList) {
        taskList.getTaskList().get(i - 1).setDone();
        return taskList;
    }

    public static TaskList removeTask(int i, TaskList taskList) {
        taskList.getTaskList().remove(i - 1);
        return taskList;
    }

    public static TaskList addTask(Task t, TaskList taskList) {
        taskList.getTaskList().add(t);
        return taskList;
    }
}
