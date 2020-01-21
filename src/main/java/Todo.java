public class Todo extends Task {
    public Todo(int id, String task) {
        super(id, task);
    }

    @Override
    public String toString() {
        if (done) return  "[T][✓] " + task;
        else return "[T][✗] " + task;
    }
}
