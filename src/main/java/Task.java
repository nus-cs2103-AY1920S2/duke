/**
 * Represents a task that users can input.
 */
public abstract class Task {
    String desc;
    boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract int compareTo(Task task);

    @Override
    public String toString() {
        String symbol = isDone ? "[\u2713] " : "[\u2718] ";
        return symbol + desc;
    }
}