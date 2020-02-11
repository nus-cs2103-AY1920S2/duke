package duke.task;

public class Task {
    public static final char TICK = '\u2713';
    public static final char CROSS = '\u2718';

    protected String description;
    protected boolean isDone;
    protected Priority priority = Priority.LOW;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;

        return t.description.equals(this.description) && (Boolean.compare(t.isDone, this.isDone)==0);
    }

    public char getStatusIcon() {
        return (isDone ? Task.TICK : Task.CROSS); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getTaskCompletionStatus() {
        return this.isDone;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }
}
