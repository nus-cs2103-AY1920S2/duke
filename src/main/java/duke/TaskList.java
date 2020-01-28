package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public Task get(int index) {
        return lst.get(index);
    }

    public void add(Task e) {
        lst.add(e);
    }

    public void remove(int e) {
        lst.remove(e);
    }

    public int size() {
        return lst.size();
    }

    public ArrayList<Task> toArr() {
        return lst;
    }

    public TaskList search(String s) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t: lst) {
            String[] arr = t.description.split(" ");
            for (String part: arr) {
                if (part.equals(s) && !res.contains(t)) {
                    res.add(t);
                }
            }
        }
        return new TaskList(res);
    }
}
