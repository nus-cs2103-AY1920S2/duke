package dukeApp.files;

public class Event extends Task {
    protected String date;
    protected String originalDate;
    DateTime dt = new DateTime();
    protected String time;

    public Event(String s, String date, String time) {
        super(s);
        originalDate = date;
        this.date = dt.convertDate(date);
        this.time = time;
    }

    /**
     * Obtain the task description in storage format where date is in YYYY-MM-DD
     * @return task description
     */
    public String storageFormat() {
        return description + "(at: " +originalDate+ " " +time+ ")";
    }

    /**
     * Returns the type of task
     *
     * @return the letter indicating the task type
     */
    public String getType() {
        return "E";
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the task description where the date is in format MMM DD YYYY
     *
     * @return task description
     */
    @Override
    public String toString() {
        return description + "(at: " +date+ " " +time+ ")";
    }
}
