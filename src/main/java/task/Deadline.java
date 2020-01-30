package task;

import core.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Specific type of task that contains description and
 * deadline of the task.
 */
public class Deadline extends Task{

    private LocalDate deadline;

    /**
     * Constructor for the deadline task
     * @param description is the detail of the task.
     * @param deadline is the deadline of the task.
     */
    public Deadline(String description, LocalDate deadline){
        super(description,"D");
        this.deadline =deadline;
    }

    /**
     * Gets the detail of the standard task and the deadline of the task.
     * @return string contains the standard format of the deadline.
     */
    @Override
    public String toString() {
        return super.toString()+" (by: "+ DateTimeUtil.standardFormat(deadline)+")";
    }
}
