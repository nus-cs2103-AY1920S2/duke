package duke.tasks;

import java.time.LocalDate;
import duke.utilities.TimeParser;

public class Deadline extends Task implements TimeParser {
    protected LocalDate deadline;

    public Deadline(String description, String deadline) { // constructor for creating new deadline
        super(description);
        this.deadline = TimeParser.parseDate(deadline);
        super.TYPE = TaskType.DEADLINE;
    }

    public Deadline(String status, String description, String deadline) { // constructor when parsing tasks from hard disk
        super(status, description);
        super.TYPE = TaskType.DEADLINE;
        this.deadline = TimeParser.parseDate(deadline);
    }

    public LocalDate getTaskTime() {
        return this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + TimeParser.printDate(this.deadline) + ")";
    }
}
