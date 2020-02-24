package duke.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Duke helps to serve as a reminder/scheduler.
 * The user's tasks are be keyed into Duke, and will be held by Duke.
 * The task class represents the task objects that will be keyed into Duke.
 *
 * @author Dargo
 */
public class Task implements Serializable {

    String task;
    String type;
    boolean isDone = false;
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    private final static String TODO = "T";
    private final static String DEADLINE = "D";
    private final static String EVENT = "E";

    /**
     * Task is the representation of a task that someone inputs into Duke.
     * A task is classified into 3 types: todo, deadline and event.
     *
     * @param type Type of task.
     * @param task Input command for the task.
     */
    public Task(String type, String task) {

        if (type.equals("todo")) {
            this.type = TODO;
        } else if (type.equals("deadline")) {
            this.type = DEADLINE;
        } else if (type.equals("event")) {
            this.type = EVENT;
        }

        this.task = task;
    }

    /**
     * Marks the task as done.
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * Formats the string that is outputted by the program.
     * Follows the style set by CS2103T website.
     *
     * @return Formatted string representing the task in question.
     * @throws DukeException If any of the task classes do not have an appropriate command input.
     */
    public String print_Format() throws DukeException {
        try {
            String checkmark = "N";

            if (this.isDone == true) {
                checkmark = "Y";
            }

            String output = "[" + this.type + "]" + "[" + checkmark + "] ";
            if (this.type.equals(EVENT)) {
                String task_Name1 = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
                String day_And_Time = task.substring(task.indexOf("/") + 4);
                output += task_Name1 + " (at: " + day_And_Time + ")";
            } else if (this.type.equals(DEADLINE)) {
                String task_Name2 = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
                String day = task.substring(task.indexOf("/") + 4);
                output += task_Name2 + " (by: " + day + ")";
            } else {
                String task_Name3 = task.substring(task.indexOf(" "));
                output += task_Name3;

            }

            return output;
        } catch (Exception e) {
            throw new DukeException(type);
        }
    }

    /**
     * Formats date into appropriate format.
     *
     * @param dateString Date input in String format.
     * @return LocalDate object in proper format.
     */
    public LocalDate formatDate(String dateString) {

        return LocalDate.parse(dateString, dateFormatter);
    }

    /**
     * Formats time into appropriate format.
     *
     * @param timeString Time input in String format.
     * @return LocalTime object in proper format.
     */
    public LocalTime formatTime(String timeString) {

        return LocalTime.parse(timeString, timeFormatter);
    }

    /**
     * Returns the formatted string.
     *
     * @return Formatted string of task object in question.
     */
    @Override
    public String toString() {

        String output = "";

        try {
            output += this.print_Format();
        } catch (DukeException e) {
            return e.toString();
        }

        return output;
    }
}
