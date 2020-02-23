import java.util.Date;

public class Event extends Task {

    private String time;
    protected String[] dateTimes;

    /**
     * This method updates the action and time accordingly for Events.
     * @param description This is the details for Event.
     * @param time This is the time for the Event.
     */
    public Event(String description, String time, String[] dateTimes) {
        super(description);
        assert description != null : "Description cannot bve empty";
        assert time != null : "Time cannot be empty";
        this.time = time;
        this.dateTimes = dateTimes;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "| at: " + time;
    }

    /**
     * This method indicates that a new Event Task has been added to the ArrayList in Store.
     */
    public String output() {
        return "Got it. I've added this task: \n" + " [E]" + super.toString() + "| at: " + time;
    }

    /**
     * This method return the time of this Event Task.
     * @return String This is the time for the Event.
     */
    public String getStrTime() {
        return dateTimes[1];
    }
}
