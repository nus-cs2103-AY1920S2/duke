package model;

import exceptions.NoDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;


public class EventTask extends Task{
    static final String TASK_TYPE_STRING = "event task";
    static final String TASK_TYPE_CHA = "E";

    protected LocalDateTime at;

    public EventTask(String description, LocalDateTime at) throws NoDescriptionException {
        super(description);
        this.at = at;
    }

    @Override
    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(
                this.description,
                this.at.format(DATE_TIME_FORMAT)));
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]"  + super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }
}
