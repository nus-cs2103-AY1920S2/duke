package task;

import core.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Specific type of task that contains description and
 * deadline of the task.
 */
public class Deadline extends Task{

    private LocalDateTime time;

    /**
     * Constructor for the deadline task
     * @param description is the detail of the task.
     * @param time is the deadline of the task.
     */
    public Deadline(String description, LocalDateTime time){
        super(description);
        this.time=time;
    }

    /**
     * Get the specific type of the task.
     * @return the type of the task.
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Get the detail of the standard task and the deadline of the task.
     * @return string contains the standard format of the deadline.
     */
    @Override
    public String toString() {
        return super.toString()+" (by: "+ DateTimeUtil.standardFormat(time)+")";
    }
}
