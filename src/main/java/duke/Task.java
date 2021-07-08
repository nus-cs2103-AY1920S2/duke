package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task contains information about a task, such as description and done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object. Initialized as undone.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the icon associated with the done status of the task.
     * Check for done and cross for undone.
     *
     * @return Done status icon.
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a Task object based of the information read from one line of the saved file.
     *
     * @param line One line from the saved file. Format: <task type> | <status> | <description> | <date>.
     * @return Task object of the correct type.
     * @throws DukeException If file is corrupt or the saved data is invalid.
     */
    public static Task getTaskFromMemory(String line) throws DukeException {
        String[] splitted = line.split(" \\| ");

        switch(splitted[0]) {
            case "T":
                return new ToDo(splitted[2]);
            case "D":
                return new Deadline(splitted[2], convertToLocalDate(splitted[3]));
            case "E":
                return new Event(splitted[2], convertToLocalDate(splitted[3]));
            default:
                throw new DukeException("Invalid file. File may have been corrupted.");
        }
    }

    /**
     * Returns a string representation of the task including its done status icon and description.
     *
     * @return String representation for task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Returns the string representation of deadline to be stored in a file.
     * The format is T | <status> | <description>.
     *
     * @return String representation to be stored in a file.
     */
    public String toFileFormat() {
        return String.format("%s | %d | %s", "T", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Converts date in String format to LocalDate format.
     * Date in String format must be of dd/mm/yyyy.
     *
     * @param input Date in String format.
     * @return Converted LocalDate.
     */
    public static LocalDate convertToLocalDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(input, formatter);
    }

    /**
     * Converts date in LocalDate format back to String format.
     *
     * @param date LocalDate date.
     * @return Date in String format of dd/mm/yyyy.
     */
    public static String convertDateToString(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        if (day < 10) {
            sb.append("0");
        }
        sb.append(String.valueOf(day) + "/");

        if (month < 10) {
            sb.append("0");
        }
        sb.append(String.valueOf(month) + "/" + year);
        return sb.toString();
    }
}
