/**
 * Deadline task that specifies a task that has to be done by a certain date
 */
package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate deadlineDate;

    /**
     * Creates a Deadline task
     * @param taskTitle Title of task to be completed
     * @param deadlineDate Deadline of task
     */
    public Deadline(String taskTitle, LocalDate deadlineDate) {
        super(taskTitle, deadlineDate, null);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns a string representation of a Deadline task
     * @return A string representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + Parser.outputDateFormatter.format(deadlineDate) + ")";
    }
}