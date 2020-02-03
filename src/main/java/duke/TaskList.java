package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task remove(int taskNumber) {
        return tasks.remove(taskNumber);
    }

    public ArrayList<Task> find(String text) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(text)) {
                result.add(t);
            }
        }
        return result;
    }
}
