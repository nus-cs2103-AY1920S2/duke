public class Events implements Task {

    private boolean done = false;
    private String name;
    private String time;

    public Events(String name, String time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public String getTaskName() {
        return name;
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    public String getTaskTime() {
        return time;
    }

    @Override
    public String getDoneString() {
        if (isDone()) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return getTaskType() + getDoneString() + " " + getTaskName() + " (at: " + getTaskTime() + ")";
    }
}
