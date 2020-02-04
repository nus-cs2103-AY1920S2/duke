package dukeApp.files;

public class Event extends Task {
    protected String date;

    public Event(String s) {
        super(s);
        description = s.split("\\(")[0];
        date = s.split("\\(")[1];
        date = date.substring(date.indexOf(" ") + 1, date.length()-1);
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
        return description + "(at: " +date+ ")";
    }
}
