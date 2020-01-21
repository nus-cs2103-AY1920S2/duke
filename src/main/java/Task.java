public class Task {
    protected int id;
    protected String task;
    protected boolean done;

    public int getId() {
        return id;
    }
    public String getTask() {
        return task;
    }
    public boolean getDone() {
        return done;
    }
    public void setDone(boolean bool) {
        done = bool;
    }

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
