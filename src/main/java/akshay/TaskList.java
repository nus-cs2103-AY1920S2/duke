package akshay;

import java.lang.reflect.Array;
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
}
