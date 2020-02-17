package dukeApp.files;

public class Deadline extends Task {
    protected String date;
    protected String time;
    protected String originalDate;
    DateTime dt = new DateTime();

    public Deadline(String s, String date, String time) {
        super(s);
        originalDate = date;
        this.date = dt.convertDate(date);
        this.time = time;
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
     * Change the date in the original format to write to file
     * Returns the task date description
     *
     * @return task date description
     */
    public String getTask() {
        return description;
    }

    /**
     * Changes the date into a specified format by passing into dukeApp.files.DateTime
     * @return changed date format
     */
    public void changeDate() {
        date = originalDate;
    }

    /**
     * Changes the time into a specified format by passing into dukeApp.files.DateTime
     * @return changed time format
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task date description
     *
     * @return task date description
     */
    @Override
    public String toString() {
        return description + "(by: " +date+ " " +time +")";
    }
}
