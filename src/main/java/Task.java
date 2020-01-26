public class Task {
    private String taskName;
    private boolean isDone;
    private char taskType;
    private String taskTime;

    public Task(String newTask, boolean isDone, char taskType, String taskTime) {
        this.taskName = newTask;
        this.isDone = isDone;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getTaskName() {
        return this.taskName;
    }

    public char getTaskType() {
        return this.taskType;
    }

    public String getDeadline() {
        return this.taskTime;
    }

    public String markDone() {
        if (this.isDone) {
            return "Oops! This task was already marked as done!";
        }
        this.isDone = true;
        return "Marked this task as done:\n    " + this;
    }

    @Override
    public String toString() {
        return "[" + taskType + "] " + "[" + getStatus() + "] " + this.taskName + this.taskTime;
    }
}
