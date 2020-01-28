package duke;
import java.util.ArrayList;


public class TaskList {
    ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    //for the loading
    TaskList(ArrayList<Task> existingList) {
        this.list = existingList;
    }
    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int getLength() {
        return list.size();
    }
}
