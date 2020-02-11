package duke.entity;

import duke.entity.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }


    public void addTask(String newTask) {

    }

    public void deleteTask(int index) {

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
