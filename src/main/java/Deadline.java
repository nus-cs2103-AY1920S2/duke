import java.util.Date;

public class Deadline extends Task {

    private String time;
    protected String[] dateTimes;

    /**
     *  This method updates the action and time accordingly for Deadline.
     * @param description This is the action to be taken.
     * @param time This is the time for which that action has to happen.
     */
    public Deadline(String description, String time, String[] dateTimes) {
        super(description);
        assert description != null : "description cannot be null";
        assert time != null : "date cannot be null";
        this.time = time;
        this.dateTimes = dateTimes;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "| by: " + time;
    }

    /**
     * This method indicates that a new Deadline Task has been added to the ArrayList in Store.
     * @return String of the completed respoonse.
     */
    public String output() {
        return "Got it. I've added this task: \n" + " [D]" + super.toString() + "| by: " + time;
    }

    /**
     * This method return the time of this Deadline Task.
     * @return STring This is time for the deadline.
     */
    public String getStrTime() {
        return dateTimes[1];
    }

}