package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/** Entity class representing a task of type Deadline */
public class Deadline extends Task {
    /** as good practice every class should have it's own private serialVersionUID */
    private static final long serialVersionUID = -5240102332818031942L;
    
    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description.strip());
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /** toString implementation */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), byDate.format(super.dateFormat), byTime);
    }
}