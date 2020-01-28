import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    String getBy() {
        return this.by;
    }


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
