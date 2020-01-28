package tojava.test;

public class Event extends Task {
    protected String word;
    protected String date;

    public Event(String s) {
        super(s);
        description = s.split("/")[0];
        date = s.split("/")[1];
        word = date.substring(0, date.indexOf(" "));
        date = date.substring(date.indexOf(" ") + 1, date.length());
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
     * Returns the task date description
     *
     * @return task date description
     */
    public String getTask() {
        return description + "(" +word+ ": " +date+ ")";
    }

    /**
     * Returns the date of task.
     *
     * @return date of task
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns at/by
     *
     * @return the word for the task date
     */
    public String getWord() {
        return word;
    }

    public String changeDate() {
        return "";
    }

    public String changeTime() {
        return "";
    }

    public String getTime() {
        return "";
    }

    /**
     * Returns only the task description without the date
     * @return task description
     */
    public String getDescription() {
        return description;
    }
}
