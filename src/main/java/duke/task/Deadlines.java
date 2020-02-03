package duke.task;

import duke.utils.Parser;

import java.util.Date;

/**
 * Deadline object extending from ExecutableTask
 */
public class Deadlines extends ExecutableTask {

    private boolean done = false;
    private String name;
    private Date time;

    /**
     * Deadline object constructor
     * @param name task name
     * @param time task time
     */
    public Deadlines(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    /**
     * check if deadline is done
     * @return done or not
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * get name of deadline
     * @return task name
     */
    @Override
    public String getTaskName() {
        return name;
    }

    /**
     * get type of task
     * @return the string D to represent deadline
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * get task time
     * @return task time
     */
    public Date getTaskTime() {
        return time;
    }

    /**
     * mark deadline as done
     */
    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName() + " (by: " +
                Parser.dateToString(getTaskTime()) + ")";
    }
}
