package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

public class TaskList {
    public ArrayList<Task> taskList;

    TaskList() {
        ArrayList<Task> taskList = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void set(int i, Task t) {
        this.taskList.set(i, t);
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public void remove(Task t) {
        this.taskList.remove(t);
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public TaskList filter(String s) {
        ArrayList<Task> al = new ArrayList<>(this.taskList.stream().filter(x -> x.getName().contains(s)).collect(Collectors.toList()));
        return new TaskList(al);
    }
}
