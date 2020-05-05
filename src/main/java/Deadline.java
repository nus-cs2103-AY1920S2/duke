import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDateTime;

/**
 * Creates a Deadline Task.
 */
public class Deadline extends Task implements Comparable<Deadline> {

    private String deadline;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private LocalDateTime dateAndTime;

    /**
     * Creates a Deadline task that inherits from Task.
     * @param description instruction of the task.
     * @param deadline date and time of which the task have to be executed by.
     */
    public Deadline(String description, String deadline) {

        super(description);
        int newDeadlineFormat = deadline.indexOf(" ");
        String actualDate = deadline.substring(newDeadlineFormat).trim(); // date without /by
        this.deadlineDate = parseDate(actualDate);
        this.deadlineTime = parseTime(actualDate);
        String formattedDate = this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        this.dateAndTime = LocalDateTime.of(this.deadlineDate, this.deadlineTime);
        this.deadline = deadline.substring(0, newDeadlineFormat) + ": " + formattedDate + "  " + formattedTime;


    }

    /**
     * Parsed time from the description data to more readable format.
     * @param actualDate the part that contains the date and time.
     * @return a LocalTime object that contains the deadline time.
     */
    private LocalTime parseTime(String actualDate) {

        int time = Integer.parseInt(actualDate.split(" ")[1]);
        LocalTime parsedTime = LocalTime.of(time / 100, time % 100);
        return parsedTime;

    }

    /**
     * Parsed date from the description data to more readable format.
     * @param actualDate the part that contains the date and time.
     * @return a LocalDate object that contains the deadline date.
     */
    private LocalDate parseDate(String actualDate) {
        String date  = actualDate.split(" ")[0];
        LocalDate deadlineDate = LocalDate.parse(date);
        return deadlineDate;

    }

    /**
     * get method for date and time of the object.
     * @return a LocalDateTime object with the deadline date and deadline time of the object.
     */
    public LocalDateTime getDateAndTime() {

        return this.dateAndTime;
    }

    /**
     * Formatted to save into hard disk.
     * @return a format that is standardised to be saved into the hard disk.
     */
    @Override
    public String saveToHardDiskFormat() {

        return String.format("D | %d | %s | %s",
                this.completedCode, this.getDescription(), this.deadline.replace("by: ", ""));
    }


    /**
     * Overrided to string method to show the type of task it is.
     * @return an extra [D] to denote that it is a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }


    /**
     * CompareTo method implemented for sorting of deadline dates.
     * @param newTask deadline object that it is being compared to.
     * @return positive if date is later than the other date, negative if it is earlier and 0 if it is equal.
     */
    @Override
    public int compareTo(Deadline newTask) {

        LocalDateTime newTaskDateAndTime = newTask.getDateAndTime();

        return this.dateAndTime.compareTo(newTaskDateAndTime);
    }
}
