package task;

import core.DateUtil;

import java.time.LocalDate;

/**
 * Specific type of task that contains description and
 * event time of the task.
 */

public class Event extends Task{

    private LocalDate eventTime;

    /**
     * Constructor for the event task
     * @param description is the detail of the task.
     * @param eventTime is the event time of the task.
     */
    public Event(String description, LocalDate eventTime){
        super(description,"E");
        this.eventTime=eventTime;
    }


    /**
     * Gets the detail of the standard task and the duration of the task.
     * @return string contains the standard format of the event.
     */
    @Override
    public String toString() {
        return super.toString()+" (at: "+ DateUtil.standardFormat(eventTime)+")";
    }
}
