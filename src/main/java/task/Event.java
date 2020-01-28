package task;

import core.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Specific type of task that contains description and
 * event time of the task.
 */

public class Event extends Task{
    private LocalDateTime time;

    /**
     * Constructor for the event task
     * @param description is the detail of the task.
     * @param time is the event time of the task.
     */
    public Event(String description, LocalDateTime time){
        super(description);
        this.time=time;
    }


    /**
     * Get the specific type of the task.
     * @return the type of the task.
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Get the detail of the standard task and the duration of the task.
     * @return string contains the standard format of the event.
     */
    @Override
    public String toString() {
        return super.toString()+" (at: "+ DateTimeUtil.standardFormat(time)+")";
    }
}
