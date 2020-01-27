import java.util.ArrayList;

public class Task {

    protected String taskDescription;
    protected String date;
    protected status isDone;

    public enum Types {
        ToDo, Deadline, Event;
    }

    public enum status {
        Y, N;
    }

    public Task(String date, String taskDescription) {

        this.taskDescription = taskDescription;
        this.date = date;
        this.isDone = status.N;

    }

    public void changeStatus(status newStatus) {

        isDone = newStatus;

    }

    public String getDate() {

        return date;

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
