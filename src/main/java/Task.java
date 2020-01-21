public class Task {
    int id;
    String task;
    boolean done;

    public Task (int id, String task) {
        this.id = id;
        this.task = task;
        done = false;
    }

    @Override
    public String toString() {
        String output = id + ".";
        if (done) output = output + "[✓] " + task;
        else output = output + "[✗] " + task;
        return output;
    }
}
