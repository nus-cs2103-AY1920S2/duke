import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

abstract class Task {
    protected String description;
    protected boolean isDone; // defaults to false

    public Task(String description) {
        this.description = description;
    }

    private String status() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** Accepts a date in the format provided in the default formatter, DateTimeFormatter.ISO_LOCAL_DATE
     */ 
    public static LocalDate toDate(String s) throws DateTimeParseException {
        return LocalDate.parse(s);
    }
    /**
     * Accepts a date according to the following 24H format: HHmm
     * @param s a string representing the time in 24H format
     * @return a LocalTime object representing the time given.
     * @throws DateTimeParseException when an invalid time is passed.
     */
    public static LocalTime toTime(String s) throws DateTimeParseException {
        int time = Integer.parseInt(s);
        return LocalTime.of(time/100, time % 100);
    }

    public String toString() {
        return String.format("[%s] %s", status(), description);
    }

    public String done() {
        isDone = true;
        return toString();
    }

}