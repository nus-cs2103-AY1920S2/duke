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
     * Change the date in the original format to write to file
     */
    public void changeDate() {
        date = originalDate;
    }

    /**
     * Returns the type of task
     *
     * @return the letter indicating the task type
     */
    public String getType() {
        return "E";
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
        return description + "(at: " +date+ " " +time+ ")";
    }
}
