package app.core.tasks;

import java.util.List;

import app.util.Date;
import app.core.StorageManager;

public class TaskManager{
    private List<Task> taskList;
    private StorageManager storageManager;

    public TaskManager() {
        this.storageManager = new StorageManager();
        this.taskList = this.storageManager.load();
    }

    public String addTodoTask(String description) {
        return this.add(new Task(description));
    }

    public String addDeadlineTask(String description, Date deadline) {
        return this.add(new DeadlineTask(description, deadline));
    }

    public String addEventTask(String description, Date when) {
        return this.add(new EventTask(description, when));
    }

    private String add(Task task) {
        this.taskList.add(task);
        this.storageManager.save(this.taskList);
        return String.format(
            "Got it. I've added this task:\n" +
            "  %s\n" +
            "Now you have %d tasks in the list.\n", task, this.taskList.size()
        );
    }

    public String setTaskDone(int index) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(index - 1);
        task.setDone();
        this.storageManager.save(this.taskList);

        return String.format(
            "Nice! I've marked this task as done: \n%s", task
        );
    }

    public String deleteTask(int index) throws IndexOutOfBoundsException {
        Task task = this.taskList.remove(index - 1);
        this.storageManager.save(this.taskList);

        return String.format(
            "Noted. I've removed this task: \n" +
            "  %s\n" +
            "Now you have %d tasks in the list.\n", task, this.taskList.size()
        );
    }

    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "You have no tasks";
        }

        StringBuilder sb = new StringBuilder("These are your tasks: \n");
        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, this.taskList.get(i)));
        }
        return sb.toString();
    }
}