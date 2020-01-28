package dukeApp.files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String word;
    protected String date;
    protected String time;
    protected String dateLine;
    protected String originalDate;

    public Deadline(String s) {
        super(s);
        description = s.split("/")[0];
        date = s.split("/")[1];
        word = date.substring(0, date.indexOf(" "));
        dateLine = date.substring(date.indexOf(" ") + 1, date.length());
        date = dateLine.split(" ")[0];
        time = dateLine.split(" ")[1];
        originalDate = date;
        date = changeDate();
        time = changeTime();
    }

    /**
     * Returns the type of task
     *
     * @return the letter indicating the task type
     */
    public String getType() {
        return "D";
    }

    /**
     * Returns the task date description
     *
     * @return task date description
     */
    public String getTask() {
        return description + "(" +word+ ": " +date+ " " +time +")";
    }

    /**
     * Returns the date before it is changed into the specified format.
     *
     * @return date of task
     */
    public String getDate() {
        return originalDate;
    }

    /**
     * Returns the time
     *
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * Changes the date into a specified format by passing into DateTime
     * @return changed date format
     */
    public String changeDate() {
        DateTime dt = new DateTime();
        return dt.convertDate(date);
    }

    /**
     * Changes the time into a specified format by passing into DateTime
     * @return changed time format
     */
    public String changeTime() {
        DateTime t = new DateTime();

        return t.convertTime(time);
    }

    /**
     * Returns at/by
     *
     * @return the word for the task date
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns only the task description without the date
     * @return task description
     */
    public String getDescription() {
        return description;
    }
}
