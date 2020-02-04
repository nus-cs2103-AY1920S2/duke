package dukeApp.files;

public class Deadline extends Task {
    protected String date;
    protected String time;
    protected String dateLine;
    protected String originalDate;
    DateTime dt = new DateTime();

    public Deadline(String s) {
        super(s);
        description = s.split("\\(")[0];
        date = s.split("\\(")[1];
        dateLine = date.substring(date.indexOf(" ") + 1, date.length()-1);
        date = dateLine.split(" ")[0];
        time = dateLine.split(" ")[1];
        originalDate = date;
        date = dt.convertDate(date);
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
