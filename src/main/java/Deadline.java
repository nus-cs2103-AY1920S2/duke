import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends AbstractTask {

    /**
     * Constructor for Deadline class.
     * @param taskName Name or description of the deadline
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of deadline
     */
//    public Deadline(String taskName, String preposition, LocalDate date) {
//        super(taskName, preposition, date);
//    }

    /**
     * Constructor for Deadline class with additional info provided after date taken to be time.
     * @param taskName Name or description of the deadline
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of deadline
     * @param time Time of deadline
     */
    public Deadline(String taskName, String preposition, LocalDate date, String... time) {
        super(taskName, preposition, date, time);
    }

    @Override
    protected String taskType() {
        return "[D]";
    }
    
}
