import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> dukeList;

    public TaskList(ArrayList<Task> dukeList) {
        this.dukeList = dukeList;
    }

    public TaskList() {
        dukeList = new ArrayList<>();
    }

    public ArrayList<Task> getDukeList() {
        return dukeList;
    }

    public void addTask(Task task) {
        dukeList.add(task);
    }

    public void removeTask(int num) {
        dukeList.remove(num-1);
    }

    public void markDone(int num) {
        dukeList.get(num - 1).markAsDone();
    }

    public int getSize() {
        return dukeList.size();
    }
}
