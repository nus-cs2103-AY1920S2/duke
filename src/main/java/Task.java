import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private char taskType;
    private boolean isDone;
    private String taskName;
    private LocalDateTime taskTime;

    public Task(char taskType, boolean isDone, String taskName, String taskTime) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.taskName = taskName;
        this.taskTime = taskTime.isEmpty()
                ? LocalDateTime.MIN
                : this.parseDate(taskTime);
    }

    public LocalDateTime parseDate(String taskTime) throws DateTimeParseException {
        if (taskType == 'D' && !taskTime.contains(" ")) {
            taskTime = taskTime + " 2359";
        }
        return LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
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

    public String getTaskTime() {
        return this.taskTime.getDayOfMonth() + "/" + this.taskTime.getMonthValue() + "/" + this.taskTime.getYear()
                + " " + this.taskTime.getHour()
                + (this.taskTime.getMinute() > 9 ? this.taskTime.getMinute() : "0" + this.taskTime.getMinute());
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
        if (this.taskType == 'T') {
            return "[" + taskType + "] " + "[" + getStatus() + "] " + this.taskName;

        } else if (this.taskType == 'D') {
            return "[" + taskType + "] " + "[" + getStatus() + "] " + this.taskName + " (by: " + this.getTaskTime() + ")";

        } else if (this.taskType == 'E') {
            return "[" + taskType + "] " + "[" + getStatus() + "] " + this.taskName + " (at: " + this.getTaskTime() + ")";
        } else {
            return "";
        }
    }
}
