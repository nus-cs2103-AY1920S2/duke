package model;

import exceptions.NoDescriptionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DeadLineTask extends Task {
    static final String TASK_TYPE_STRING = "deadline task";
    static final String TASK_TYPE_CHA = "D";

    protected LocalDateTime by;

    public DeadLineTask() {}

    public DeadLineTask(String description, LocalDateTime by) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
    }

    @Override
    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(
                this.description,
                this.by.format(DATE_TIME_FORMAT)));
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }
}
