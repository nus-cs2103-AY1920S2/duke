import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

/**
 * returns the toString of deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     *
     * @param description is the name of the task
     * @param by deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    String getBy() {
        return this.by;
    }

    /**
     *
     * @param at takes in the deadline date of the task
     * @return it as MMM d yyyy
     */
    String getDate(String at) {
        String s = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
            DateFormat newFormat = new SimpleDateFormat("MMM d yyyy h a");
            s =  newFormat.format(df.parse(at));
        }catch (ParseException pe) {
            pe.printStackTrace();
        }
        return s;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate(this.by) + ")";
    }
}
