public class Todos implements Task {

    private boolean done = false;
    private String name;

    public Todos(String name) {
        this.name = name;
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
        return "T";
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
    public String getTaskTime() {
        return "@%";
    }

    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName();
    }
}
