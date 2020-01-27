package duke.Task;

import java.time.LocalDateTime;

public class Task {

    protected String taskDescription;
    protected LocalDateTime dateTime;
    protected status isDone;

    public enum Types {
        ToDo, Deadline, Event;
    }

    public enum status {
        Y, N;
    }

    public Task(LocalDateTime dateTime, String taskDescription) {

        this.taskDescription = taskDescription;
        this.dateTime = dateTime;
        this.isDone = status.N;

    }

    public void changeStatus(status newStatus) {

        isDone = newStatus;

    }

    public LocalDateTime getDateTime() {

        return dateTime;

    }

    public status getStatus() {

        return isDone;

    }

    public String getTaskDescription() {

        return taskDescription;

    }

    public Task.Types getType() {
        return this.getType();
    }


    @Override
    public String toString() {

        return this.toString();

    }
}
