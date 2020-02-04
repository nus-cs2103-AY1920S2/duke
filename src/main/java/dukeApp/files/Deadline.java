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
     */
    public void changeDate() {
        date = originalDate;
    }

    /**
     * Returns only the task description without the date
     * @return task description
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
