package duke;
import java.util.ArrayList;


public class TaskList {
    ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

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

    @Override
    public String toString() {
        return list.toString();
    }

    public TaskList find(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (Task t : list) {
            if (t.input.contains(keyword)) {
                matchingList.add(t);
            }
        }
        return new TaskList(matchingList);
    }

}
