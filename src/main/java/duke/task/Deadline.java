package duke.task;

import duke.DukeException;

import java.time.format.DateTimeFormatter;

/**
 * The type Deadline which extends from Task.
 * Handles all inputs which has "deadline..."
 */
public class Deadline extends Task {

    // by is completion date.
    // d1 is the date that is being parsed
    // Time is the deadline completion time (IF THERE IS!)
    private String by;


    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     */
    public Deadline(String description) {
        super(description);
    }


    // One way to get the time is we split the string, then we check if there
    // Is more character elements after the year.
    // If there is, then it means that there is time inclusive

    /**
     * Sets the date and time of the deadline task.
     *
     * @param by the by
     */
    // Then for the date wise right, can just check if there is only one element
    // Or two elements.
    public void setBy(String by) {
        this.by = super.set_by_at(by);
    }

    private String getBy() {
        return by;
    }

    /**
     * Sets d1, the LocalDateTime for the Task.
     *
     * @throws DukeException the duke exception
     */
    public void setD1() throws DukeException {
        super.setD1(getBy());
    }

    /**
     * From the input given by the user, filter out the commands (Deadline)
     * And returns the description of the string.
     * @param s the s
     * @return String without "deadline"
     * @throws DukeException when the user enters an empty description or use the wrong deadline format
     */

    //Get the task from the given input.
    @Override
    public String formatTasks(String s) throws DukeException {
        String[] splitedString = s.split("deadline ");
        if (splitedString.length < 1) {
            throw new DukeException("You cannot leave the description empty");
        } else {
            try {
                return s.substring(s.indexOf("by")).replaceAll("by ", "");

            } catch (Exception e) {
                throw new DukeException("Please use ../by instead of any other identifiers ");
            }
        }

    }

    /**
     * Gets description of the task. WWithout the date and time
     * returns the deadline task description.
     */
    @Override
    public void setDescription(String s) throws DukeException {
        try {
            String deadlineTask = s.substring(s.indexOf("deadline"), s.indexOf("/"));
            String deadlineTask2 = deadlineTask.replaceAll("deadline", "").trim();
            super.setDescription(deadlineTask2);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for a deadline");
        }
    }

    @Override
    public String toString() {
        return " [" + TaskCode.D + "]" + super.toString() + " (by: "
                + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

}
