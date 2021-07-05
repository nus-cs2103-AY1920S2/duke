package data.task;

import common.Messages;

import java.time.LocalDate;

public class Task {

    /** common attributes*/
    protected int taskIndex;
    protected String taskDescription;
    protected boolean isDone;
    protected char taskType;

    /** deadline and event task attributes*/
    protected String taskTime;
    /** deadline task attributes*/
    protected LocalDate taskDeadlineDate;

    public Task() {
    }

//    /**
//     * called by
//     */
//    public Task(int taskIndex, String taskDescription) {
//        this.taskIndex = taskIndex;
//        this.taskDescription = taskDescription;
//        this.isDone = false;
//    }

    /**
     * called by ToDo constructor
     */
    public Task(int taskIndex,String taskDescription, char taskType) {
        this.taskIndex = taskIndex;
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.isDone = false;
        this.taskTime = "-1";
        this.taskDeadlineDate = LocalDate.MAX;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public char getChar(){
        char isDone;
        if (this.isDone){
            return Messages.DONE;
        } else {
            return Messages.notDONE;
        }
    }

    public char getTaskType() {
        return taskType;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public String getTaskDeadline() {
        return taskTime;
    }

    public LocalDate getTaskDeadlineDate() {
        return taskDeadlineDate;
    }


    public String getTaskStartTime() {
        return taskTime;
    }

    public String getTaskEndTime() {
        return taskTime;
    }

    /**
     * Returns true if both tasks have the same identity fields (name and telephone).
     */
    boolean isSameTask(Task other) {
        return (other == this)
                || (other != null
                && other.getTaskDescription().equals(this.getTaskDescription()));
    }
}
