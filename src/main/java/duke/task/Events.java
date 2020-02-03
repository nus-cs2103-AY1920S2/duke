package duke.task;

import duke.utils.Parser;

import java.util.Date;

/**
 * Event object implementing task interface
 */
public class Events implements Task {

    private boolean done = false;
    private String name;
    private Date time;

    /**
     * Event object constructor
     * @param name task name
     * @param time task time
     */
    public Events(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    /**
     * check if event is done
     * @return done or not
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * get name of event
     * @return task name
     */
    @Override
    public String getTaskName() {
        return name;
    }

    /**
     * get type of task
     * @return the string E to represent event
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * get task time
     * @return task time
     */
    public Date getTaskTime() {
        return time;
    }

    /**
     * get done string
     * @return string to represent task state
     */
    @Override
    public String getDoneString() {
        if (isDone()) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    /**
     * mark event as done
     */
    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName() + " (at: " +
                Parser.dateToString(getTaskTime()) + ")";
    }
}
