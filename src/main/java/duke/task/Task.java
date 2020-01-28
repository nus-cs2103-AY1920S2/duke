package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected String taskName = "";
    protected boolean isDone;
    protected LocalDate dateTime;
    protected String taskType = "";

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, String dateTime) {
        this.taskName = taskName;
        this.isDone = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateTime = LocalDate.parse(dateTime, formatter);
    }

    // To initialise save data for duke.task.ToDo
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    // To initialise save data for duke.task.Deadline and duke.task.Event
    public Task(String taskName, boolean isDone, String dateTime) {
        this.taskName = taskName;
        this.isDone = isDone;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateTime = LocalDate.parse(dateTime, formatter);
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public String getSaveFormat() {
        return this.taskType + "_" + this.isDone + "_" + this.taskName + "_" + this.dateTime;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

}
