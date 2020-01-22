public class Task {
    private boolean isCompleted;
    private String description;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    private String getStatusIcon() {
        return (this.isCompleted ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }
}
