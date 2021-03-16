package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Deadline is a subtype of Task.
 * An event is meant to be for when a task should be done before a certain date.
 * Eg. Return book 19/01/2020
 *
 * @author Dargo
 */
public class Deadline extends Task {

    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Deadline subtype of Task.
     *
     * @param type Type of task.
     * @param task Input command for the task.
     * @throws DukeException When input command is not valid.
     */
    public Deadline(String type, String task) throws DukeException{
        super(type, task);


        try {
            String[] deadline = task.substring(task.indexOf("/")).split(" ");

            if (deadline.length != 3) {
                throw new DukeException("dateTime");
            }

            String deadlineDateString = deadline[1];
            String deadlineTimeString = deadline[2];
            this.deadlineDate = formatDate(deadlineDateString);
            this.deadlineTime = formatTime(deadlineTimeString);

        } catch (Exception e) {
            throw new DukeException("dateTime");
        }

    }

    /**
     * Returns the formatted string.
     *
     * @return Formatted string of task object in question.
     */
    @Override
    public String toString() {

        String checkmark = "N";

        if (this.isDone == true) {
            checkmark = "Y";
        }

        String output = "[" + this.type + "]" + "[" + checkmark + "] ";

        String taskName = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
        output += taskName + " (by: " + deadlineDate.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + deadlineTime.format(
                            DateTimeFormatter.ofPattern("h:mm a")) + ")";
        return output;
    }
}
