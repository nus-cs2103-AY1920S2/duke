import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * returns the toString of Event.
 */
public class Event extends Task {
    private String at;

    /**
     *
     * @param description is the name of the task.
     * @param at to be done on this date.
     */
    public Event(String description, String at) {
        super(description);
        assert description != null : " no date and timing for this event";
        this.at = at;
        assert at != null : " no date and timing for this event";
    }

    String getAt() {
        return this.at;
    }

    /**
     *
     * @param at takes in the event's date.
     * @return it as MMM d yyyy.
     */
    String date(String at) {
        assert at != null : " no date and timing available";
        String s = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
            DateFormat newFormat = new SimpleDateFormat("MMM d yyyy h a");
            s =  newFormat.format(df.parse(at));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return s;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date(at) + ")";
    }
}
