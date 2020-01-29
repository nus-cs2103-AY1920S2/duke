import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> storage;

    public TaskList(ArrayList storage) throws DukeException {
        if (storage == null) {
            throw new DukeException("empty file");
        } else {
            this.storage = storage;
        }
    }

    public TaskList() {}

    public ArrayList<Task> getStore() {
        return storage;
    }

    public void addTask(Task t) {
        storage.add(t);
    }

    public void removeTask(int i) {
        storage.remove(i);
    }

    public Task retrieveTask(int i) {
        return storage.get(i);
    }

    public int getSize() {
        return storage.size();
    }

}
