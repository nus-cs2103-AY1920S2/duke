package duke.task;

import duke.utils.Parser;

import java.util.Date;

/**
 * Event object extending from ExecutableTask.
 */
public class Event extends ExecutableTask {

    private boolean done = false;
    private String name;
    private Date time;

    /**
     * Event object constructor.
     * @param name task name
     * @param time task time
     */
    public Event(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    /**
     * checks if event is done.
     * @return done or not
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * gets name of event.
     * @return task name
     */
    @Override
    public String getTaskName() {
        return name;
    }

    /**
     * gets type of task.
     * @return the string E to represent event
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * gets task time.
     * @return task time
     */
    public Date getTaskTime() {
        return time;
    }

    /**
     * marks event as done.
     */
    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName() + " (at: "
                + Parser.dateToString(getTaskTime()) + ")";
    }
}
