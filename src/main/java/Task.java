import java.util.ArrayList;

public class Task {

    static int totalTask = 0;

    protected int index;
    protected String taskDescription;
    protected String date;
    protected boolean isDone;

    public enum Types {
        ToDo, Deadline, Event
    }

    public enum status {
        Y, N
    }

    public Task(int index, String date, String taskDescription) {
        this.index = index;
        this.taskDescription = taskDescription;
        this.date = date;
        this.isDone = false;
    }

    public void changeStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public String getDate() {
        return date;
    }

    public String getTaskDescription() {
        return taskDescription;
    }


    @Override
    public String toString() {
        return this.toString();
    }
}
