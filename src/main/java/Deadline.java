import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

/**
 * Deadline stores the description of task and the deadline.
 */
public class Deadline extends Task {

    /**
     * contains the date of the deadline.
     */
    private String by;

    /**
     *
     * @param description is the name of the task
     * @param by deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != null: "No description for this deadline";
        this.by = by;
        assert by != null : " no date and timing for this deadline";
    }

    String getBy() {
        return this.by;
    }

    /**
     * @param by takes in the deadline date of the task.
     * @return it as MMM d yyyy.
     */
    String parseDate(String by) {
        assert by != null : " no date and timing available";
        String s = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
            DateFormat newFormat = new SimpleDateFormat("MMM d yyyy h a");
            s =  newFormat.format(df.parse(by));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return s;
    }

    /**
     * @return the date of the deadline.
     */
    String getDate() {
        return this.by.split(" ")[0];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate(this.by) + ")";
    }
}
