package seedu.java.util;

public class Task{
    protected Complete complete;
    protected String task;

    public Task(String task){
        this.complete = new Complete(false);
        this.task = task;
    }

    public Task(String task, boolean bool){
        this.complete = new Complete(bool);
        this.task = task;
    }

    /**
     * Converts Complete from true to False
     * @return the task with complete as true
     */
    public Task completeTask() {
        this.complete = new Complete(true);
        return this;
    }

    /**
     * Returns a boolean on whether the task is completed
     * @return completed.isCompleted()
     */
    public boolean getComplete(){
        return complete.isCompleted();
    }

    /**
     * Returns a String of task
     * @return String task
     */
    public String getTask(){
        return task;
    }

    @Override
    public String toString(){
        return ". " + "[ ] " + complete + task;
    }
}