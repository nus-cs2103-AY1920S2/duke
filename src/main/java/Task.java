public class Task {
    int id;
    String task;

    public Task (int id, String task) {
        this.id = id;
        this.task = task;
    }

    @Override
    public String toString() {
        return id + ". " + task;
    }
}
