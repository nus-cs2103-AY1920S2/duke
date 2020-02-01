package duke.entity.task;

import java.util.Date;

public abstract class Task {

    private boolean isDone;
    private String taskName;
    private String addedInfo;

    public Task() {
        this.isDone = false;
        this.taskName = "";
    }

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAddedInfo() {
        return addedInfo;
    }

    public void setAddedInfo(String addedInfo) {
        this.addedInfo = addedInfo;
    }

    /**
     * Returns the task in a String form to be printed in a pre-determined format
     *
     * @return the String to be printed
     */
    public abstract String printTask();

    /**
     * Returns the task in a String form to be saved in a pre-determined format
     *
     * @return the String to be saved
     */
    public abstract String toStringForm();

    /**
     * Checks the task if it's due by/held before the date provided.
     * Todo tasks and events/deadlines without a proper date detected will not be included.
     *
     * @param date the date to be checked
     * @return whether the task is due/held before the date
     */
    public abstract boolean isDue(Date date);

}
