package seedu.java.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String timing;
    private LocalDate recordedDate;

    /**
     * The default comnstructor for deadline.
     * @param task - piece of task to-do
     * @param timing - the deadline of the task
     */
    public Deadline(String task, String timing) {
        super(task);
        this.timing = timing;
        convertDate();
    }

    /**
     * An alternate Deadline constructor that can modify completion status. Intended for storage class.
     * @param task - piece of task to-do
     * @param timing - the deadline of the task
     * @param bool - Completion status: is it completed?
     */
    public Deadline(String task, String timing, boolean bool) {
        super(task, bool);
        this.timing = timing;
        convertDate();
    }

    private void convertDate() {
        this.recordedDate = LocalDate.parse(timing.split(" ")[1]);
    }

    public String getTiming() {
        return timing;
    }

    public String getTime() {
        return this.recordedDate.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    @Override
    public String toString() {
        return  ". " + "[D] " + complete + task + "(" + timing.split(" ")[0].split("/")[0] + " " + getTime() + ")";
    }
}